package com.example.demo.model;

/**
 * This class represents the User without exposing his password
 * 
 * @author Giovana Brito Oliveira
 *
 */
public class StudentDTO {

	/**
	 * StudentDTO constructor
	 * 
	 * @param firstName    the user's first name
	 * @param lastName     the user's last name
	 * @param email        the user's email
	 * @param confirmation token
	 */
	public StudentDTO(String firstName, String lastName, String email, String token) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.token = token;
	}

	private String firstName;
	private String lastName;
	private String email;
	private String token;

	/**
	 * Returns the user's first name
	 * 
	 * @return user's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Updates the user's first name
	 * 
	 * @param firstName user's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the user's last name
	 * 
	 * @return user's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Updates the user's last name
	 * 
	 * @param firstName user's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the user's email
	 * 
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the user's token
	 * 
	 * @return user's token
	 */
	public String getToken() {
		return token;
	}

}