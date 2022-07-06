package kmusau.zeraki.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "studentID")
	private int studentID;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private CourseEntity course; 
	
	@Column(name = "institution")
	private String institution;
	
	@Column(name = "regnumber")
	private String RegNumber;

	public StudentEntity(int studentID, String name, CourseEntity course, String institution, String regNumber) {
		super();
		this.studentID = studentID;
		this.name = name;
		this.course = course;
		this.institution = institution;
		this.RegNumber = regNumber;
	}


	public StudentEntity() {
		super();
	}


	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getRegNumber() {
		return RegNumber;
	}

	public void setRegNumber(String regNumber) {
		RegNumber = regNumber;
	}  
	
	
}
