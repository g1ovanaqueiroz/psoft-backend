package com.example.demo.controller;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.user.StudentNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

import com.example.demo.exception.InvalidPasswordException;

/**
 * 
 * @author giovana
 *
 */
@RestController
@RequestMapping("/v1/auth")
public class LoginController {
	
	private final String TOKEN_KEY = "banana";
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody Student student) throws StudentNotFoundException, InvalidPasswordException {

		Student authUser = studentService.findByEmail(student.getEmail());
		
		if(authUser == null) {
			throw new StudentNotFoundException("Usuario nao encontrado!");
		}
		
		if(!authUser.getPassword().equals(student.getPassword())) {
			throw new InvalidPasswordException("Senha invalida!");
		}
		
		String token = Jwts.builder().
				setSubject(authUser.getEmail()).
				signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
				setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.compact();
		
		return new LoginResponse(token);
		
	}
	
	private class LoginResponse {
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
		
		String getToken() {
			return this.token;
		}
	}
	
}