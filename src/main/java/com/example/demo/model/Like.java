package com.example.demo.model;

/**
 * This class represents a like
 * 
 * @author Giovana Brito Oliveira
 *
 */
public class Like {

	String email;
	long id;

	/**
	 * Like constructor
	 * 
	 * @param email user email
	 * @param id    subject id
	 */
	public Like(String email, long id) {
		this.email = email;
		this.id = id;
	}

	/**
	 * Return the subject id
	 * 
	 * @return long id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Return tha user email
	 * 
	 * @return string email
	 */
	public String getEmail() {
		return this.email;
	}
}
