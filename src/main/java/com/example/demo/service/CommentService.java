package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CommentDAO;
import com.example.demo.exception.user.CommentNotFoundException;
import com.example.demo.model.Comment;

/**
 * Offers some services to the controller
 * 
 * @author Giovana Brito Oliveira
 *
 */
@Service
public class CommentService {

	private final CommentDAO commentDAO;

	/**
	 * CommentService constructor
	 * 
	 * @param commentDAO CommentDAO
	 */
	CommentService(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	/**
	 * Create a Comment
	 * 
	 * @param comment Comment object
	 * @return Created comment
	 */
	public Comment create(Comment comment) {
		return commentDAO.save(comment);
	}

	/**
	 * Update a comment
	 * 
	 * @param commentToUpdate
	 * @return updated comment
	 * @throws CommentNotFoundException
	 */
	public Comment update(Comment commentToUpdate) throws CommentNotFoundException {

		Comment comment = commentDAO.findById(commentToUpdate.getId());
		if (comment == null)
			throw new CommentNotFoundException("Could not update. The product does not exist.");

		return commentDAO.save(commentToUpdate);
	}

	/**
	 * Delete a Comment from the Database
	 * 
	 * @param id Comment id
	 */
	public void delete(long id) {
		commentDAO.deleteById(id);
	}

	/**
	 * Finds a Comment through his id
	 * 
	 * @param id Comment id
	 * @return Comment
	 */
	public Comment findById(long id) {
		return commentDAO.findById(id);
	}
}