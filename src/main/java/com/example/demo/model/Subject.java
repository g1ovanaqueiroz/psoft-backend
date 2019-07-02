package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class represents a Subject
 * 
 * @author Giovana Brito Oliveira
 *
 */
@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private HashSet<String> likes;
	private ArrayList<Comment> comments;

	public Subject() {

	}

	/**
	 * Subject constructor
	 * 
	 * @param name  subject name
	 * @param likes list of e-mails
	 */
	public Subject(String name) {
		this.name = name;
		this.likes = new HashSet<String>();
		this.comments = new ArrayList<Comment>();
	}

	/**
	 * Return the subject id
	 * 
	 * @return subject id number
	 */
	public long getId() {
		return id;
	}

	/**
	 * Return the subject name
	 * 
	 * @return subject name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Updates the subject name
	 * 
	 * @param name subject name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return a Subject representation
	 * 
	 * @return toString
	 */
	public String ToString() {
		return this.getId() + " - " + this.getName();
	}

	/**
	 * Return the list of e-mails
	 * 
	 * @return likes
	 */
	public HashSet<String> getLikes() {
		return this.likes;
	}

	/**
	 * Add a like from this subject profile
	 * 
	 * @param email user email
	 */
	public void addLike(String email) {
		this.likes.add(email);
	}

	/**
	 * Remove the like from this subject profile
	 * 
	 * @param email
	 */
	public void removeLike(String email) {
		this.likes.remove(email);
	}

	/**
	 * Returns a Boolean indicating whether the user passed as a parameter has or
	 * has not liked the subject
	 * 
	 * @param email user email
	 * @return boolean
	 */
	public boolean itLiked(String email) {
		return this.likes.contains(email);
	}

	/**
	 * Add a comment
	 * 
	 * @param text  comment text
	 * @param email user email
	 */
	public void addComment(String text, String email) {
		Comment comment = new Comment(text, email);
		comments.add(comment);
	}
}