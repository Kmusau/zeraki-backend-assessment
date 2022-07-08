package kmusau.zeraki.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
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
	
	@GetMapping("/allstudents")
	public List<StudentEntity> allStudents() {
		return studentrepo.findAll();
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
		} catch(EmptyResultDataAccessException e) {
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
	@PutMapping("/student/{studentID}/changecourse/{courseID}")
	public StudentEntity editStudentCourse(@PathVariable int studentID, @PathVariable int courseID) {
		StudentEntity student = studentService.getSingleStudent(studentID).get();
		CourseEntity course = courseService.getSingleCourse(courseID);
		student.setCourse(course);
		return studentrepo.save(student);
	}
	
	//Transfer the student to another university and assign them a course.
	@PutMapping("/student/changeinstitution/{id}")
	public StudentEntity editStudentInstitutionAndCourse(@RequestBody StudentEntity student, @PathVariable int id) {
		return studentService.editStudent(student, id);
	}
	
	
	//list of courses - filter the courses by searching
	@GetMapping("/courses/fetch")
	public List<CourseEntity> getAllCourses(@Param("keyword") String keyword) {
		return courseService.getAllCourses(keyword);
	}
	
	//Create a course
	@PostMapping("/course/create")
	public CourseEntity addCourse(@RequestBody CourseEntity course) {
		courseService.addCourse(course);
		return course;
	}
	
	//Edit the name of a course.
	@PutMapping("/course/editname/{id}")
		public CourseEntity editCourseName(@RequestBody CourseEntity course, @PathVariable int id) {
		return courseService.editCourse(course, id);
	}
	
	//Delete course
	@DeleteMapping("/course/delete/{id}")
	public String deleteCourse(@PathVariable int id) {
		try {
			return courseService.deleteCourse(id);
		} catch(NoSuchElementException e) {
			return "No such element found";
		}
	}
	
	//Add course to an institution
	@PutMapping("/institution/{institutionID}/course/{courseID}")
	public String addCourseToInstitution (@PathVariable int institutionID, @PathVariable int courseID) {
		InstitutionEntity institution = institutionService.getSingleInstitution(institutionID);
		CourseEntity course = courseService.getSingleCourse(courseID);
		if(institution.getCourses().contains(course)) {
			return "The institution already offers that course";
		} else {
			institution.addCourseToInstitution(course);
			institutionrepo.save(institution);
			return "Updated Successfully";
		}
		
	}
	
	//List all institutions. -- sort the list of institutions by name
	@GetMapping("/institutions/fetch")
	public List<InstitutionEntity> getAllInstitutions(@Param("keyword") String keyword) {
		return institutionService.getAllInstitutions(keyword);
	}
	
	//Add a new institution
	@PostMapping("/institution/create")
	public InstitutionEntity addInstitution(@RequestBody InstitutionEntity institution) {
		return institutionService.addInstitution(institution);
	}
	
	//Delete institution
	@DeleteMapping("/institution/delete/{id}")
	public String deleteInstitution(@PathVariable int id) {
		try {
			return institutionService.deleteInstitution(id);
		} catch(NoSuchElementException e) {
			return "No such element found";
		}
	}
	
	
	//Edit the name of an institution.
	@PutMapping("/institution/editname/{id}")
	public InstitutionEntity editInstitutionName(@RequestBody InstitutionEntity institution, @PathVariable int id) {
		return institutionService.editInstitution(institution, id);
	}
	
}
