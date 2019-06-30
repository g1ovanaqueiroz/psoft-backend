package com.example.demo.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Subject;

@Repository
public interface SubjectDAO<T, ID extends Serializable> extends JpaRepository<Subject, Long> {

   Subject save(Subject subject);

   Subject findById(long id);
   
   @Query (value = "Select * from Subject s where s.name = :pname", nativeQuery = true)
   Subject findByName(@Param("pname") String name);
   
   @Query(value = "Select s FROM Subject s WHERE s.name LIKE concat('%',:substring,'%')")
   List<Subject> findBySubstring(@Param("substring") String substring);
}