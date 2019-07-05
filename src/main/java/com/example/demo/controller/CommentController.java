package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.user.CommentNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Represents a comment
 * 
 * @author Giovana Brito Oliveira
 *
 */
@Api
@RestController
@CrossOrigin(origins = "*")
@RequestMapping({ "/v1/comments" })
public class CommentController {

	private CommentService commentService;

	/**
	 * CommentController constructor
	 * 
	 * @param commentService CommentService
	 */
	CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * Finds a comment through his id
	 * 
	 * @param id comment id
	 * @return long id
	 */
	@ApiOperation(value = "Finds a comment through his id")
	@GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<Comment> findById(@PathVariable long id) {
		Comment comment = commentService.findById(id);

		if (comment == null) {
			throw new CommentNotFoundException("Product not found");
		}

		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}

	/**
	 * Create a Comment on Database
	 * 
	 * @param comment Comment
	 * @return new created comment
	 */
	@ApiOperation(value = "Create a Comment on Database")
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<Comment> create(@RequestBody Comment comment) {
		Comment newComment = commentService.create(comment);

		if (newComment == null) {
			throw new InternalError("Something went wrong");
		}

		return new ResponseEntity<Comment>(newComment, HttpStatus.CREATED);
	}

	/**
	 * Deletes a comment from database
	 * 
	 * @param id comment id
	 * @return HttpStatus
	 */
	@ApiOperation(value = "Deletes a comment from database")
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity delete(@PathVariable long id) {
		try {
			commentService.delete(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * Updates a comment
	 * 
	 * @param comment Comment
	 * @return updated comment
	 */
	@ApiOperation(value = "Updates a comment from database")
	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<Comment> update(@RequestBody Comment comment) {
		try {
			Comment updated = commentService.update(comment);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * Return a list of all the answers of a particular comment
	 * 
	 * @param commentId Comment ID
	 * @return List of comments
	 */
	@ApiOperation(value = "Return a list of all the answers of a particular comment")
	@GetMapping(value = "/answers/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<List<Comment>> findAnswers(@PathVariable long commentId) {
		try {
			return new ResponseEntity<List<Comment>>(commentService.findAnswers(commentId), HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * Return an integer indicating how many comments a particular subject has
	 * 
	 * @param subjectId Subject id
	 * @return integer
	 */
	@ApiOperation(value = "Return an integer indicating how many comments a particular subject has")
	@GetMapping(value = "/count/{subjectId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<Integer> countComment(@PathVariable long subjectId) {
		try {
			return new ResponseEntity<Integer>(commentService.countComment(subjectId), HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * Return a list of all the comments of a particular subject
	 * 
	 * @param subjectId Subject ID
	 * @return list of comments
	 */
	@ApiOperation(value = "Return a list of all the comments of a particular subject")
	@GetMapping(value = "/subject/{subjectId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<List<Comment>> subjectComments(@PathVariable long subjectId) {
		try {
			return new ResponseEntity<List<Comment>>(commentService.subjectComments(subjectId), HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * Return all the comments
	 * 
	 * @return list of comments
	 */
	@ApiOperation(value = "Return all the comments")
	@GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<List<Comment>> findAll() {
		return new ResponseEntity<List<Comment>>(commentService.findAll(), HttpStatus.OK);
	}

	/**
	 * Return all the not deleted comments
	 * 
	 * @return list of comments
	 */
	@ApiOperation(value = "Return all the not deleted comments")
	@GetMapping(value = "/notDeleteds", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<List<Comment>> findAllNotDeleted() {
		try {
			return new ResponseEntity<List<Comment>>(commentService.findAllNotDeleted(), HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}
}
