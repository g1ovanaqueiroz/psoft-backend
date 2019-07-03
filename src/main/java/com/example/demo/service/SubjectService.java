package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentDAO;
import com.example.demo.dao.SubjectDAO;
import com.example.demo.exception.user.SubjectNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;

/**
 * Offers services to Controller
 * 
 * @author Giovana Brito Oliveira
 *
 */
@Service
public class SubjectService {

	private final SubjectDAO subjectDAO;

	/**
	 * SubjectService constructor
	 * 
	 * @param subjectDAO SubjectDAO
	 */
	SubjectService(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}

	/**
	 * Creates a new Subject
	 * 
	 * @param subject
	 * @return new Subject
	 */
	public Subject create(Subject subject) {
		return subjectDAO.save(subject);
	}

	/**
	 * Updates a subject
	 * 
	 * @param subjectToUpdate
	 * @return updated subject
	 * @throws SubjectNotFoundException
	 */
	public Subject update(Subject subjectToUpdate) throws SubjectNotFoundException {

		Subject subject = subjectDAO.findById(subjectToUpdate.getId());
		if (subject == null)
			throw new SubjectNotFoundException("Could not update. The subjrect does not exist.");

		return subjectDAO.save(subjectToUpdate);
	}

	/**
	 * Deletes a subject through his id number
	 * 
	 * @param id id number
	 */
	public void delete(long id) {
		subjectDAO.deleteById(id);
	}

	/**
	 * Find a subject through his id number
	 * 
	 * @param id id number
	 * @return Subject
	 */
	public Subject findById(long id) {
		return subjectDAO.findById(id);
	}

	/**
	 * Find a subject through his name
	 * 
	 * @param name
	 * @return Subject
	 */
	public Subject findByName(String name) {
		return subjectDAO.findByName(name);
	}

	/**
	 * Find subjects that have this substring in their names
	 * 
	 * @param substring string
	 * @return Subject List
	 */
	public List<Subject> findBySubstring(String substring) {
		return subjectDAO.findBySubstring(substring);
	}

	/**
	 * Return all the Subjects
	 * 
	 * @return Subject List
	 */
	public List<Subject> findAll() {
		return subjectDAO.findAll();
	}

	/**
	 * Add a like in a subject
	 * 
	 * @param id    subject id
	 * @param email user email
	 * @return updated Subject
	 */
	public Subject addLike(long id, String email) {
		Subject subject = subjectDAO.findById(id);
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found!");
		}
		subject.addLike(email);
		return subjectDAO.save(subject); // updates the subject on database
	}

	/**
	 * Remove a like in a subject
	 * 
	 * @param id    subject id
	 * @param email user email
	 * @return updated subject
	 */
	public Subject removeLike(long id, String email) {
		Subject subject = subjectDAO.findById(id);
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found!");
		}
		subject.removeLike(email);
		return subjectDAO.save(subject); // updates the subject on database
	}

	/**
	 * Returns how many likes the subject has
	 * 
	 * @param id subject id
	 * @return quantity of likes
	 */
	public int countLikes(long id) {
		Subject subject = subjectDAO.findById(id);
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found!");
		}
		return subject.countLikes();
	}

	/**
	 * Returns a Boolean indicating whether the user passed as a parameter has or
	 * has not liked the subject
	 * 
	 * @param id    subject id
	 * @param email user id
	 * @return boolean
	 */
	public boolean itLiked(long id, String email) {
		Subject subject = subjectDAO.findById(id);
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found!");
		}
		return subject.itLiked(email);
	}
}