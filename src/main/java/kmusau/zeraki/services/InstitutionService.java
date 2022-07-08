package kmusau.zeraki.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import kmusau.zeraki.entities.CourseEntity;
import kmusau.zeraki.entities.InstitutionEntity;
import kmusau.zeraki.entities.StudentEntity;
import kmusau.zeraki.repositories.InstitutionRepository;

@Service
public class InstitutionService {

	@Autowired
	InstitutionRepository institutionrepo;

	public InstitutionEntity getSingleInstitution(int id) {
		return institutionrepo.findById(id).get();
	}
	
	public List<InstitutionEntity> getAllInstitutions(String keyword) {
		if (keyword != null) {
            return institutionrepo.search(keyword);
        }
		return institutionrepo.findAll(Sort.by(Sort.Direction.ASC, "institutionName"));
	}
	
	public InstitutionEntity addInstitution(@RequestBody InstitutionEntity institution) {
		institutionrepo.save(institution);
		return institution;
	}
	
	public String deleteInstitution(int id) {
		InstitutionEntity institution = getSingleInstitution(id);
		List<CourseEntity> checkcourses = institution.getCourses();
		
		if(checkcourses.isEmpty()) {
			institutionrepo.deleteById(id);
			return "Deleted successfully";
		} else {
			return "Institution has some courses assigned to it!! Cannot be deleted";
		}
	}
	
	public InstitutionEntity editInstitution(InstitutionEntity institution, int id) {
		InstitutionEntity inst = institutionrepo.findById(id).get();
		
		if (Objects.nonNull(institution.getInstitutionName())) {
			inst.setInstitutionName(institution.getInstitutionName());
        }
		if (Objects.nonNull(institution.getLocation())) {
			inst.setLocation(institution.getLocation());
        }
		if (Objects.nonNull(institution.getCourses())) {
			inst.setCourses(institution.getCourses());
        }

		InstitutionEntity updatedInstitution = institutionrepo.save(inst);
		
		return updatedInstitution;
	}
	
}
