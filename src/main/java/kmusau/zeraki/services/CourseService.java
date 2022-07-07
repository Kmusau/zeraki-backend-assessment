package kmusau.zeraki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kmusau.zeraki.entities.CourseEntity;
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
	
	public List<CourseEntity> getAllCourses() {
		return courserepo.findAll(Sort.by(Sort.Direction.ASC, "courseName"));
	}
}
