package com.example.demo.model;

/**
 * This class represents the login informations
 * 
 * @author Giovana Brito Oliveira
 *
 */
public class Login {

	private String email;
	private String password;

	/**
	 * Login contructor
	 * 
	 * @param email    user email
	 * @param password user password
	 */
	public Login(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * Return the user email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Return the user password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

}
