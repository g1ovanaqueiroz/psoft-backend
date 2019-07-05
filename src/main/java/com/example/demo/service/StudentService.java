package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentDAO;
import com.example.demo.exception.user.StudentNotFoundException;
import com.example.demo.model.Student;

import sendEmail.SpringEmailController;

/**
 * 
 * Offers some services to the controller
 * 
 * @author Giovana Brito Oliveira
 *
 */
@Service
public class StudentService {

	private final StudentDAO studentDAO;
	private SpringEmailController mail;

	/**
	 * StudentService constructor
	 * 
	 * @param studentDAO StudentDAO
	 */
	StudentService(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		this.mail = new SpringEmailController();
	}

	/**
	 * Creates an user
	 * 
	 * @param student
	 * @return new Student
	 */
	public Student create(Student student) {
		mail.sendWellcomeEmail(student.getEmail());
		return studentDAO.save(student);
	}

	/**
	 * Updates an user
	 * 
	 * @param studentToUpdate
	 * @return Updated Student
	 * @throws StudentNotFoundException
	 */
	public Student update(Student studentToUpdate) throws StudentNotFoundException {

		Student user = studentDAO.findByEmail(studentToUpdate.getEmail());
		if (user == null)
			throw new StudentNotFoundException("Could not update. The product does not exist.");

		return studentDAO.save(studentToUpdate);
	}

	/**
	 * Deleted an user through this email
	 * 
	 * @param email user email
	 */
	public void delete(String email) {
		studentDAO.deleteById(email);
	}

	/**
	 * Find an user through his email
	 * 
	 * @param email user email
	 * @return Student
	 */
	public Student findByEmail(String email) {
		return studentDAO.findByEmail(email);
	}

	/**
	 * Find an user through his email and his password
	 * 
	 * @param email    user email
	 * @param password user password
	 * @return Student
	 */
	public Student findByLogin(String email, String password) {
		return studentDAO.findByLogin(email, password);
	}
}