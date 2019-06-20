package com.example.demo.model;

public class UserDTO {
	
	public UserDTO(String firstName, String lastName, String email, String token) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.token = token;
	}
	
	private String firstName;
	private String lastName;
	private String email;
	private String token;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
