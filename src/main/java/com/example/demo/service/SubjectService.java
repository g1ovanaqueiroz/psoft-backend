package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.SubjectDAO;
import com.example.demo.exception.user.SubjectNotFoundException;
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

	public List<Subject> findBySubstring(String substring) {
		return subjectDAO.findBySubstring(substring);
	}
	
	public List<Subject> findAll() {
		return subjectDAO.findAll();
	}
}