package com.example.demo.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This error will be thrown when the subject is not found
 * 
 * @author Giovana Brito Oliveira
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SubjectNotFoundException extends RuntimeException {

	/**
	 * SubjectNotFoundException constructor
	 * 
	 * @param msg msg
	 */
	public SubjectNotFoundException(String msg) {
		super(msg);
	}
}