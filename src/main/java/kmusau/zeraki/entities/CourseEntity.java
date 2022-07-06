package kmusau.zeraki.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class CourseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "courseID")
	private int courseID;
	
	@Column(name = "course_name")
	private String courseName;
	
	//FIXME: mapped by
	@OneToMany
	private List<StudentEntity> students;
	
	@ManyToMany		
	private List<InstitutionEntity> institutions;

	public CourseEntity(int courseID, String courseName, List<StudentEntity> students, List<InstitutionEntity> institutions) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.students = students;
		this.institutions = institutions;
	}


	public CourseEntity() {
		super();
	}


	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}

	public List<InstitutionEntity> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<InstitutionEntity> institutions) {
		this.institutions = institutions;
	}
//	
	
}
