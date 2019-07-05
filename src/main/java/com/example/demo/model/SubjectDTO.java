package com.example.demo.model;

/**
 * This class represents a subject without protected variables
 * 
 * @author Giovana Brito Oliveira
 *
 */
public class SubjectDTO {

	String text;
	int number;

	/**
	 * SubjectsDTO constructor
	 * 
	 * @param text   String containing code and name of the subjectc
	 * @param number It could represent the number of likes or comments, depending
	 *               on which class is using him
	 */
	public SubjectDTO(String text, int number) {
		this.text = text;
		this.number = number;
	}

	/**
	 * Returns the text
	 * 
	 * @return string text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Returns the number
	 * 
	 * @return int number
	 */
	public int getNumber() {
		return this.number;
	}

}
