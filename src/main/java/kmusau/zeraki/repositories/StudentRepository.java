package kmusau.zeraki.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import kmusau.zeraki.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{

	
	Page<StudentEntity> getStudentsByInstitution(@Param("institution") String Institution, Pageable pageable);

}
