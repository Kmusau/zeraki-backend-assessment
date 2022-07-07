package kmusau.zeraki.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "institutions")
public class InstitutionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "institutionID")
	private int institutionID;
	
	@Column(name = "institution_name", unique=true)
	private String institutionName;
	
	@ManyToMany
	@JoinTable(name = "institutions_courses", joinColumns = @JoinColumn(name = "institutionid"), inverseJoinColumns = @JoinColumn(name = "courseid"))
	private List<CourseEntity> courses;
	
	@Column(name = "location")
	private String location;

	public InstitutionEntity(int institutionID, String institutionName, List<CourseEntity> courses, String location) {
		super();
		this.institutionID = institutionID;
		this.institutionName = institutionName;
		this.courses = courses;
		this.location = location;
	}

	public InstitutionEntity() {
		super();
	}

	public int getInstitutionID() {
		return institutionID;
	}

	public void setInstitutionID(int institutionID) {
		this.institutionID = institutionID;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public List<CourseEntity> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseEntity> courses) {
		this.courses = courses;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void addCourseToInstitution(CourseEntity course) {
		courses.add(course);
	}
}
