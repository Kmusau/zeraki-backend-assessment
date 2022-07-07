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
import kmusau.zeraki.repositories.StudentRepository;
import kmusau.zeraki.services.CourseService;
import kmusau.zeraki.services.InstitutionService;
import kmusau.zeraki.services.StudentService;

@RestController
public class Controller {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	InstitutionService institutionService;
	
	@Autowired
	InstitutionRepository institutionrepo;
	
	@Autowired
	StudentRepository studentrepo;
	

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
	
	//Add a student
	@PostMapping("/student/add")
	public StudentEntity addStudent(@RequestBody StudentEntity student) {
		studentService.addStudent(student);
		return student;
	}
	
	//assign student a course
	@PutMapping("/student/{studentID}/course/{courseID}")
	public StudentEntity assignStudentACourse (@PathVariable int studentID, @PathVariable int courseID) {
		StudentEntity student = studentService.getSingleStudent(studentID).get();
		CourseEntity course = courseService.getSingleCourse(courseID);
		student.setCourse(course);
		return studentrepo.save(student);
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
		return courseService.getAllCourses();
	}
	
	@PostMapping("/course/create")
	public CourseEntity addCourse(@RequestBody CourseEntity course) {
		courseService.addCourse(course);
		return course;
	}
	
	//List all institutions. -- sort the list of institutions by name
	@GetMapping("/institutions/fetch")
	public List<InstitutionEntity> getAllInstitutions() {
		return institutionService.getAllInstitutions();
	}
	
	//Add a new institution
	@PostMapping("/institution/create")
	public InstitutionEntity addInstitution(@RequestBody InstitutionEntity institution) {
		return institutionService.addInstitution(institution);
	}
	
	//Delete institution
	@DeleteMapping("/institution/delete/{id}")
	public String deleteInstitution(@PathVariable int id) {
		return institutionService.deleteInstitution(id);
	}
	
	//Add course to an institution
	@PutMapping("/institution/{institutionID}/course/{courseID}")
	public InstitutionEntity addCourseToInstitution (@PathVariable int institutionID, @PathVariable int courseID) {
		InstitutionEntity institution = institutionService.getSingleInstitution(institutionID);
		CourseEntity course = courseService.getSingleCourse(courseID);
		institution.addCourseToInstitution(course);
		return institutionrepo.save(institution);
	}
	
	//Edit the name of a student.
	@PutMapping("/institution/editname/{id}")
	public InstitutionEntity editInstitutionName(@RequestBody InstitutionEntity institution, @PathVariable int id) {
		return institutionService.editInstitution(institution, id);
	}
}
