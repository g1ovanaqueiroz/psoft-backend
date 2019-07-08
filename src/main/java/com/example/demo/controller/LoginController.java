package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.user.StudentNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.example.demo.model.Login;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

import com.example.demo.exception.InvalidPasswordException;

/**
 * Login Controller
 * 
 * @author giovana Brito Oliveira
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/auth")
public class LoginController {

	private final String TOKEN_KEY = "magicword";

	@Autowired
	private StudentService studentService;

	/**
	 * makes the login authentication
	 * 
	 * @param login user email and password
	 * @return LoginResponse
	 * @throws StudentNotFoundException
	 * @throws InvalidPasswordException
	 */
	@CrossOrigin
	@PostMapping(value="/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse authenticate(@RequestBody Login login)
			throws StudentNotFoundException, InvalidPasswordException {

		Student authUser = studentService.findByEmail(login.getEmail());

		if (authUser == null) {
			throw new StudentNotFoundException("Usuario nao encontrado!");
		}

		if (!authUser.getPassword().equals(login.getPassword())) {
			throw new InvalidPasswordException("Senha invalida!");
		}

		String token = Jwts.builder().setSubject(authUser.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 1800000)).compact();

		return new LoginResponse(token);

	}

	/**
	 * Contains the authentication token
	 * 
	 * @author Giovana Brito Oliveira
	 *
	 */
	private class LoginResponse {
		public String token;

		/**
		 * LoginResponse constructor
		 * 
		 * @param token authentication token
		 */
		public LoginResponse(String token) {
			this.token = token;
		}

		/**
		 * Returns the authentication token
		 * 
		 * @return token
		 */
		String getToken() {
			return this.token;
		}
	}

}