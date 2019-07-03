package com.example.demo.dao;

import com.example.demo.model.Comment;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
 * Comment Repository
 */
@Repository
public interface CommentDAO<T, ID extends Serializable> extends JpaRepository<Comment, Long> {

	/**
	 * Save a Comment on Database
	 */
	Comment save(Comment comment);

	/**
	 * Finds a comment through his id
	 * 
	 * @param id comment id
	 * @return comment
	 */
	Comment findById(long id);

	/**
	 * Return a list of all the comments
	 */
	List<Comment> findAll();

	/**
	 * Return a list of all the comments of a particular subject
	 * 
	 * @param subjectId Subject id
	 * @return List
	 */
	@Query(value = "SELECT c FROM Comment c WHERE c.subjectReference=:pId")
	List<Comment> subjectComments(@Param("pId") long subjectId);

	/**
	 * Return a list of all the answers of a particular comment
	 * 
	 * @param commentId Comment id
	 * @return List of comments
	 */
	@Query(value = "SELECT c FROM Comment c WHERE c.commentReference=:pId")
	List<Comment> findAnswers(@Param("pId") long commentId);
}