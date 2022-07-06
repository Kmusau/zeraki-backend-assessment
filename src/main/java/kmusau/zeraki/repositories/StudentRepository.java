package kmusau.zeraki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import kmusau.zeraki.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{

}
