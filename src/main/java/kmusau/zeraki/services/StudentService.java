package kmusau.zeraki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kmusau.zeraki.entities.StudentEntity;
import kmusau.zeraki.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentrepo;
	
	public List<StudentEntity> getAllStudents() {
		return studentrepo.findAll();	
	}
	
	public void addStudent(StudentEntity student) {
		studentrepo.save(student);
	}
	
	public String deleteStudent(int id) {
		studentrepo.deleteById(id);
		return "Deleted successfully";
	}
}
