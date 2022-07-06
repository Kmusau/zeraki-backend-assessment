package kmusau.zeraki.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kmusau.zeraki.entities.CourseEntity;
import kmusau.zeraki.entities.InstitutionEntity;
import kmusau.zeraki.entities.StudentEntity;
import kmusau.zeraki.repositories.CourseRepository;
import kmusau.zeraki.repositories.InstitutionRepository;
import kmusau.zeraki.services.StudentService;

@RestController
public class Controller {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseRepository courserepo;
	
	@Autowired
	InstitutionRepository institutionrepo;
	

	@GetMapping("/v1/test")
	public String welcome() {
		return "Getting started ";
	}
	
	//List all students in each institution, showing 10 at a time. 
	//Sorting by course
	//FIXME: add another param - filter by course
	@GetMapping("/students/fetch")
	public List<StudentEntity> getAllStudents(@RequestParam(defaultValue = "0") int pageNo, @RequestParam String institution) {
		return studentService.getAllStudents(pageNo, institution);
	}
	
	@GetMapping("/students/fetch/{id}")
	public Optional<StudentEntity> getSingleStudent(@PathVariable int id) {
		return studentService.getSingleStudent(id);
	}
	
	//Add a student and assign them a course.
	@PostMapping("/student/add")
	public StudentEntity addStudent(@RequestBody StudentEntity student) {
		studentService.addStudent(student);
		return student;
	}
	
	//Delete a particular student.
	@DeleteMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable int id) {
		try {
			studentService.deleteStudent(id);
			return "Deleted successfully";
		} catch(NoSuchElementException e) {
			return "No such element found";
		}
	}
	
	@PutMapping("/student/update/{id}")
	public StudentEntity editStudent(@RequestBody StudentEntity student, @PathVariable int id) {
		return studentService.editStudent(student, id);
	}
	
	//Edit the name of a student.
	@PutMapping("/student/editname/{id}")
	public StudentEntity editStudentName(@RequestBody StudentEntity student, @PathVariable int id) {
		return studentService.editStudent(student, id);
	}
	
	//Change the course the student is doing within the same institution.
	@PutMapping("/student/changecourse/{id}")
	public StudentEntity editStudentCourse(@RequestBody StudentEntity student, @PathVariable int id) {
		return studentService.editStudent(student, id);
	}
	
	//Transfer the student to another university and assign them a course.
	@PutMapping("/student/changeinstitution/{id}")
	public StudentEntity editStudentInstitutionAndCourse(@RequestBody StudentEntity student, @PathVariable int id) {
		return studentService.editStudent(student, id);
	}
	
	
	@GetMapping("/courses/fetch")
	public List<CourseEntity> getAllCourses() {
		
		return courserepo.findAll();
	}
	
	@GetMapping("/institutions/fetch")
	public List<InstitutionEntity> getAllInstitutions() {
		
		return institutionrepo.findAll();
	}
}
