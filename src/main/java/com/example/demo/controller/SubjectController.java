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

import com.example.demo.exception.user.SubjectNotFoundException;
import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;

/**
 * Subject Controller
 * 
 * @author Giovana Brito Oliveira
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping({ "/v1/subjects" })
public class SubjectController {

	private SubjectService subjectService;

	/**
	 * SubjectController constructor
	 * 
	 * @param subjectService SubjectService
	 */
	SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	/**
	 * Finds a Subject through his ID Number
	 * 
	 * @param id Subject ID
	 * @return Subject
	 */
	@CrossOrigin
	@GetMapping(value = "/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Subject> findById(@PathVariable long id) {
		Subject subject = subjectService.findById(id);

		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found");
		}

		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}

	/**
	 * Finds a Subject through his name
	 * 
	 * @param name Subject name
	 * @return Subject
	 */
	@CrossOrigin
	@GetMapping(value = "/name/{name}", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Subject> findByName(@PathVariable String name) {
		Subject subject = subjectService.findByName(name);

		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found");
		}

		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}

	/**
	 * Finds the Subjects that have this substring in their names
	 * 
	 * @param substring
	 * @return Subject List
	 */
	@CrossOrigin
	@GetMapping(value = "/substring/{substring}", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Subject>> findBySubstring(@PathVariable String substring) {
		List<Subject> subjects = subjectService.findBySubstring(substring);

		if (subjects == null) {
			throw new SubjectNotFoundException("Subject not found");
		}

		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}

	/**
	 * Creates a new Subject
	 * 
	 * @param subject
	 * @return new Subject
	 */
	@CrossOrigin
	@PostMapping(value = "/create", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Subject> create(@RequestBody Subject subject) {
		Subject newSubject = subjectService.create(subject);

		if (newSubject == null) {
			throw new InternalError("Something went wrong");
		}

		return new ResponseEntity<Subject>(newSubject, HttpStatus.CREATED);
	}

	/**
	 * Deletes a Subject through his id number
	 * 
	 * @param id Subject id number
	 * @return HttpStatus
	 */
	@CrossOrigin
	@DeleteMapping(value = "/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity delete(@PathVariable long id) {
		try {
			subjectService.delete(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * Updates a Subject
	 * 
	 * @param subject Subject to update
	 * @return HttpStatus
	 */
	@CrossOrigin
	@PutMapping(value = "/", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject> update(@RequestBody Subject subject) {
		try {
			Subject updated = subjectService.update(subject);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * 
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/")
	public ResponseEntity<List<Subject>> findAll() {
		List<Subject> list = subjectService.findAll();
		return new ResponseEntity<List<Subject>>(list, HttpStatus.OK);
	}
}