package com.example.demo.controller;

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

/**
 * Represents a comment
 * 
 * @author Giovana Brito Oliveira
 *
 */
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
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
	 * Deletes a comment on database
	 * 
	 * @param id comment id
	 * @return HttpStatus
	 */
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

}
