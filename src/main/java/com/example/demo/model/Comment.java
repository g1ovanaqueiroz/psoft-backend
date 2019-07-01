package com.example.demo.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class represents a comment
 * 
 * @author Giovana Brito Oliveira
 *
 */
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	String text;
	String userEmail;
	Calendar calendar;

	public Comment() {

	}

	/**
	 * Comment constructor
	 * 
	 * @param text      comment text
	 * @param userEmail user email
	 */
	public Comment(String text, String userEmail) {
		this.text = text;
		this.userEmail = userEmail;
		this.calendar = Calendar.getInstance();
	}

	/**
	 * Return a boolean indicating whether this comment is or is not null
	 * 
	 * @return boolean
	 */
	public boolean isNIL() {
		return this.userEmail == null;
	}

	/**
	 * Return the comment text
	 * 
	 * @return string
	 */
	public String getText() {
		return text;
	}

	/**
	 * Updates the comment text
	 * 
	 * @param text string
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Return the user email
	 * 
	 * @return email
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Update the user email
	 * 
	 * @param userEmail email
	 */
	private void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Return the comment date
	 * 
	 * @return date
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * Return the comment id
	 * 
	 * @return id
	 */
	public long getId() {
		return this.id;
	}

}
