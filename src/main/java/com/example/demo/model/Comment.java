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

	long subjectReference;
	long commentReference;
	String text;
	String userEmail;
	Calendar calendar;
	boolean deleted;

	/**
	 * Empty comment constructor
	 */
	public Comment() {

	}

	/**
	 * Simple comment constructor
	 * 
	 * @param text             comment text
	 * @param userEmail        user email
	 * @param subjectReference subject reference
	 */
	public Comment(String text, String userEmail, long subjectReference) {
		this.text = text;
		this.userEmail = userEmail;
		this.subjectReference = subjectReference;
		this.calendar = Calendar.getInstance();
		this.commentReference = 0;
		this.deleted = false;
	}

	/**
	 * Answer comment constructor
	 * 
	 * @param text             comment text
	 * @param userEmail
	 * @param subjectReference
	 * @param commentReference
	 */
	public Comment(String text, String userEmail, long subjectReference, long commentReference) {
		this.text = text;
		this.userEmail = userEmail;
		this.subjectReference = subjectReference;
		this.commentReference = commentReference;
		this.calendar = Calendar.getInstance();
		this.deleted = false;
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

	/**
	 * Returns a boolean indicating whether the comment is an answer
	 * 
	 * @return boolean
	 */
	public boolean isAnswer() {
		return this.commentReference != 0;
	}

	/**
	 * Return the Subject reference
	 * 
	 * @return long id
	 */
	public long getSubjectReference() {
		return subjectReference;
	}

	/**
	 * Return the Comment Reference
	 * 
	 * @return long id
	 */
	public long getCommentReference() {
		return commentReference;
	}

	/**
	 * Return a boolean indicating whether the comment is deleted
	 * 
	 * @return boolean
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Updates the deletion indicator
	 * 
	 * @param deleted boolean
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
