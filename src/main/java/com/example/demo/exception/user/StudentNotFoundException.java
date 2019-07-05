package com.example.demo.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This error will be thrown when the student is not found
 * 
 * @author Giovana Brito Oliveira
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {

	/**
	 * StudentNotFoundException constructor
	 * 
	 * @param msg msg
	 */
	public StudentNotFoundException(String msg) {
		super(msg);
	}
}
