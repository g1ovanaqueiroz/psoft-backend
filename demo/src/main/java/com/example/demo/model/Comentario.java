package com.example.demo.model;


import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	String text;
	String userEmail;
	Calendar calendar;
	
	public Comentario() {
		
	}
	
	public Comentario(String text, String userEmail) {
		this.text = text;
		this.userEmail = userEmail;
		this.calendar = Calendar.getInstance();
	}
	
	public boolean isNIL() {
		return this.text == null;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserEmail() {
		return userEmail;
	}

	private void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	private void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

}
