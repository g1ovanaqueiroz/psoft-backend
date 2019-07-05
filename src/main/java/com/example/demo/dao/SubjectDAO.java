package com.example.demo.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Subject;

/**
 * Subject repository
 * 
 * @author Giovana Brito Oliveira
 *
 * @param <T> Subject
 * @param <ID> Id number
 */
@Repository
public interface SubjectDAO<T, ID extends Serializable> extends JpaRepository<Subject, Long> {

	/**
	 * Save a new Subject
	 */
	Subject save(Subject subject);

	/**
	 * Find a Subject through his id number
	 * 
	 * @param id subject id number
	 * @return Subject
	 */
	Subject findById(long id);

	/**
	 * Find a Subject through his name
	 * 
	 * @param name subject name
	 * @return Subject
	 */
	@Query(value = "Select * from Subject s where s.name = :pname", nativeQuery = true)
	Subject findByName(@Param("pname") String name);

	/**
	 * Find subjects that have this substring in their names
	 * 
	 * @param substring
	 * @return Subject List
	 */
	@Query(value = "Select s FROM Subject s WHERE s.name LIKE concat('%',:substring,'%')")
	List<Subject> findBySubstring(@Param("substring") String substring);

	/**
	 * return a list of all the subjects
	 */
	List<Subject> findAll();

}