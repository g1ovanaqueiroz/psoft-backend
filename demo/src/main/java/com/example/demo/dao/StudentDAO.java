package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;

@Repository
public interface StudentDAO<T, ID> extends JpaRepository<Student, String> {

	Student save(Student student);
	
	@Query(value="Select s from Student s where s.email=:pemail")
    Student findByEmail(@Param("pemail") String email);
	
	@Query(value="Select s from Student s where s.email=:pemail and s.password=:ppassword")
	Student findByLogin(@Param("pemail") String email, @Param("ppassword") String password);
}