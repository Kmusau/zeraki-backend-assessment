package kmusau.zeraki.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kmusau.zeraki.entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer>{

	@Query("SELECT p FROM CourseEntity p WHERE CONCAT(p.courseName) LIKE %?1%")
	public List<CourseEntity> search(String keyword);
}
