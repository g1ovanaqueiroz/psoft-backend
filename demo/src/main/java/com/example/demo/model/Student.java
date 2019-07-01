package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * This class represents an user object
 * 
 * @author Giovana Brito Oliveira
 *
 */
@Entity
public class Student {

	@Id
	private String email;

	private String firstName;
	private String lastName;
	private String password;

	public Student() {

	}

	/**
	 * Student constructor
	 * 
	 * @param firstName user first name
	 * @param lastName  user last name
	 * @param email     user email
	 * @param password  user password
	 */
	public Student(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/**
	 * Return the user first name
	 * 
	 * @return string
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
	 * @param lastName user last name
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
	 * Updates the user email
	 * 
	 * @param email user email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Return the user password
	 * 
	 * @return user password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Updates the user password
	 * 
	 * @param password user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}