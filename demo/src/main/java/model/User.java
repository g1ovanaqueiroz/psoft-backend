package model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * This class represents an user object
 * @author Giovana Brito Oliveira
 *
 */
@Entity
public class User {
	
	@Id
	private String email;
	private String firstName;
	private String lastName;
	private String password;

	public User() {

	}

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	// returns the users first name
	public String getFirstName() {
		return firstName;
	}

	// updates the users first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// returns the users last name
	public String getLastName() {
		return lastName;
	}

	// updates the users last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// returns the users email
	public String getEmail() {
		return email;
	}

	// updates the users email
	public void setEmail(String email) {
		this.email = email;
	}

	//// returns the users password
	public String getPassword() {
		return password;
	}

	// updates the users password
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}