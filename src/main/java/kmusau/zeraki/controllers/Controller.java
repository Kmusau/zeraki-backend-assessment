package kmusau.zeraki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kmusau.zeraki.entities.StudentEntity;
import kmusau.zeraki.services.StudentService;

@RestController
public class Controller {
	
	@Autowired
	StudentService studentService;

	@GetMapping("/")
	public String welcome() {
		return "Welcome Home";
	}
	
	@GetMapping("/students/fetch")
	public List<StudentEntity> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@PostMapping("/student/add")
	public List<StudentEntity> addStudent(@RequestBody StudentEntity student) {
		studentService.addStudent(student);
		return studentService.getAllStudents();
	}
	
	@DeleteMapping("/student/delete/{id}")
	public void deleteStudent(@PathVariable int id) {
		studentService.deleteStudent(id);
	}
}
