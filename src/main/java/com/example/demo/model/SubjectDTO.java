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

	public SubjectDTO(String text, int number) {
		this.text = text;
		this.number = number;
	}

	public String getText() {
		return this.text;
	}

	public int getNumber() {
		return this.number;
	}

}
