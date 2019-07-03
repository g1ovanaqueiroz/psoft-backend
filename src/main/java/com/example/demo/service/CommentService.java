package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

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
			throw new CommentNotFoundException("Could not update. The comment does not exist.");

		return commentDAO.save(commentToUpdate);
	}

	/**
	 * Turn a comment deleted
	 * 
	 * @param id Comment id
	 */
	public void delete(long id) {
		Comment comment = commentDAO.findById(id);

		if (comment == null) {
			throw new CommentNotFoundException("Comment not found!");
		}
		comment.setDeleted(true);
		commentDAO.save(comment);
	}

	/**
	 * Return an integer indicating how many comments a particular subject has
	 * 
	 * @param id Subject Id
	 * @return int
	 */
	public int countComment(long id) {
		List<Comment> comments = commentDAO.subjectComments(id);
		return comments.size();
	}

	/**
	 * Return a list of all the comments of a particular subject
	 * 
	 * @param subjectId Subject Id
	 * @return List of comments
	 */
	public List<Comment> subjectComments(long subjectId) {
		return commentDAO.subjectComments(subjectId);
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

	/**
	 * Return all the comments
	 * 
	 * @return List of comments
	 */
	public List<Comment> findAll() {
		return commentDAO.findAll();
	}

	/**
	 * Return a list of all the answers of a particular comment
	 * 
	 * @param commentId Comment id
	 * @return List of comments
	 */
	public List<Comment> findAnswers(long commentId) {
		return commentDAO.findAnswers(commentId);
	}

	/**
	 * Return all the not deleted comments
	 * 
	 * @return list of comments
	 */
	public List<Comment> findAllNotDeleted() {
		List<Comment> allComments = commentDAO.findAll();
		List<Comment> notDeleteds = new ArrayList<Comment>();

		for (int i = 0; i < allComments.size(); i++) {
			Comment comment = allComments.get(i);
			if (!comment.isDeleted()) {
				notDeleteds.add(comment);
			}
		}
		return notDeleteds;
	}
}