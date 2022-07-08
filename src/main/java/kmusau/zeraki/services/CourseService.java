package kmusau.zeraki.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kmusau.zeraki.entities.CourseEntity;
import kmusau.zeraki.entities.InstitutionEntity;
import kmusau.zeraki.entities.StudentEntity;
import kmusau.zeraki.repositories.CourseRepository;


@Service
public class CourseService {
	
	@Autowired
	CourseRepository courserepo;

	public CourseEntity getSingleCourse(int id) {
		return courserepo.findById(id).get();
	}
	
	public void addCourse(CourseEntity course) {
		courserepo.save(course);
	}
	
	public List<CourseEntity> getAllCourses(String keyword) {
		if (keyword != null) {
            return courserepo.search(keyword);
        }
		return courserepo.findAll(Sort.by(Sort.Direction.ASC, "courseName"));
	}
	
	public String deleteCourse(int id) {
		CourseEntity course = getSingleCourse(id);
		List<StudentEntity> checkstudents = course.getStudents();
		
		if(checkstudents.isEmpty()) {
			courserepo.deleteById(id);
			return "Deleted successfully";
		} else {
			return "Course has some students assigned to it!! Cannot be deleted";
		}
	}
	
	public CourseEntity editCourse(CourseEntity course, int id) {
		CourseEntity acourse = courserepo.findById(id).get();
		
		if (Objects.nonNull(course.getCourseName())) {
			acourse.setCourseName(course.getCourseName());
        }
		if (Objects.nonNull(course.getInstitutions())) {
			acourse.setInstitutions(course.getInstitutions());
        }
		if (Objects.nonNull(course.getStudents())) {
			acourse.setStudents(course.getStudents());
        }
		CourseEntity updatedCourse = courserepo.save(acourse);
		
		return updatedCourse;
	}
}
