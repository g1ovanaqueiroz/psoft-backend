package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This error will be thrown when the password is invalid
 * 
 * @author Giovana Brito Oliveira
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidPasswordException extends RuntimeException {

	/**
	 * InvaliPasswordException constructor
	 * 
	 * @param msg msg
	 */
	public InvalidPasswordException(String msg) {
		super(msg);
	}
}
