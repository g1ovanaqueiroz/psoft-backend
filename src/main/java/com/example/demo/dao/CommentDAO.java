package com.example.demo.dao;

import com.example.demo.model.Comment;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
