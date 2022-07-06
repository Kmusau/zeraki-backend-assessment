package kmusau.zeraki.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kmusau.zeraki.entities.StudentEntity;
import kmusau.zeraki.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentrepo;
	
	public List<StudentEntity> getAllStudents(int pageNo, String institution) {
		
		Pageable paging = PageRequest.of(pageNo, 10, Sort.by("course"));
		
		Page<StudentEntity> pagedResult = studentrepo.getStudentsByInstitution(institution, paging);
		
		if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<StudentEntity>();
        }
	}
	

	public Optional<StudentEntity> getSingleStudent(int id) {
		return studentrepo.findById(id);
	}
 	
	public void addStudent(StudentEntity student) {
		studentrepo.save(student);
	}
	
	public void deleteStudent(int id) {
		studentrepo.deleteById(id);
	}
	
	public StudentEntity editStudent(StudentEntity student, int id) {
		StudentEntity stude = studentrepo.findById(id).get();
		
		if (Objects.nonNull(student.getName())) {
	            stude.setName(student.getName());
	        }
		if (Objects.nonNull(student.getCourse())) {
            stude.setCourse(student.getCourse());
        }
		if (Objects.nonNull(student.getInstitution())) {
            stude.setInstitution(student.getInstitution());
        }
		if (Objects.nonNull(student.getRegNumber())) {
            stude.setRegNumber(student.getRegNumber());
        }
		StudentEntity updatedStudent = studentrepo.save(stude);
		
		return updatedStudent;
	}
}
