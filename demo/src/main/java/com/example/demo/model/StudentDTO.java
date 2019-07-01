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
	 * @param firstName    the user first name
	 * @param lastName     the user last name
	 * @param email        the user email
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
	 * Return the user first name
	 * 
	 * @return user first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Updates the user first name
	 * 
	 * @param firstName user first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Return the user last name
	 * 
	 * @return user last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Updates the user last name
	 * 
	 * @param firstName user last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Return the user email
	 * 
	 * @return user email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Return the user token
	 * 
	 * @return user token
	 */
	public String getToken() {
		return token;
	}

}