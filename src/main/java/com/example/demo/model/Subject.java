package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	@Column(name = "name")
	private String name;
	
	@ElementCollection(targetClass=String.class)
	private Set<String> likes;

	public Subject() {
		this.likes = new HashSet<String>();
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
	public Set<String> getLikes() {
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

}