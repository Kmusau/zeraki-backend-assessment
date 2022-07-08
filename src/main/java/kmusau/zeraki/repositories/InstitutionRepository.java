package kmusau.zeraki.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kmusau.zeraki.entities.InstitutionEntity;

public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Integer>{

	@Query("SELECT p FROM InstitutionEntity p WHERE CONCAT(p.institutionName) LIKE %?1%")
	public List<InstitutionEntity> search(String keyword);
}
