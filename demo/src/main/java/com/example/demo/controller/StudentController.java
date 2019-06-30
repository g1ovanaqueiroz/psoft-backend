package com.example.demo.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.InvalidPasswordException;
import com.example.demo.exception.user.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.StudentDTO;
import com.example.demo.service.StudentService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * 
 * User Controller - manages the creation, deletion and updating of users
 * @author Giovana Brito Oliveria
 *
 */
@RestController
@RequestMapping("/v1/students")
public class StudentController {

	private StudentService studentService;

	StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	//This method returns the token of an existing user
	@GetMapping(value = "/login")
	@ResponseBody
	public ResponseEntity<StudentDTO> login(@RequestBody String email, String password) {
		Student newUser = studentService.findByLogin(email, password);
		final String TOKEN_KEY = "magicword";
		 
		if (newUser == null) {
			throw new StudentNotFoundException("Usuario nao encontrado!");
		}
		
		if(!newUser.getPassword().equals(password)) {
			throw new InvalidPasswordException("Senha invalida!");
		}
		
		String token = Jwts.builder().
				setSubject(newUser.getEmail()).
				signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
				setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.compact();
		
		StudentDTO userDTO = new StudentDTO(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), token);
		return new ResponseEntity<StudentDTO>(userDTO, HttpStatus.FOUND);
	}

	// Student creation method
	@PostMapping(value = "/signup")
	@ResponseBody
	public ResponseEntity<StudentDTO> singup(@RequestBody Student student) {
		Student newStudent = studentService.create(student);
		final String TOKEN_KEY = "magicword";

		if (newStudent == null) {
			throw new InternalError("Something went wrong");
		}
		
		String token = Jwts.builder().
				setSubject(newStudent.getEmail()).
				signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
				setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.compact();
		
		StudentDTO studentDTO = new StudentDTO(student.getFirstName(), student.getLastName(), student.getEmail(), token);
		
		return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.CREATED);
	}
	
	//Student deletion method
	@DeleteMapping(value = "/delete")
	   public ResponseEntity delete(@PathVariable String email) {
	       try {
	           studentService.delete(email);
	           return new ResponseEntity(HttpStatus.OK);
	       } catch (Exception e) {
	           throw new InternalError("Something went wrong");
	       }
	   }
	
	//Student updating method
	@PutMapping(value = "/")
	   public ResponseEntity<StudentDTO> update(@RequestBody Student student) {
	       try {
	           Student updated = studentService.update(student);
	           
	           String token = Jwts.builder().
	   				setSubject(updated.getEmail()).
	   				signWith(SignatureAlgorithm.HS512, "secretword").
	   				setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
	   				.compact();
	   		
	   		StudentDTO userDTO = new StudentDTO(updated.getFirstName(), updated.getLastName(), updated.getEmail(), token);
	   		return new ResponseEntity<StudentDTO>(userDTO, HttpStatus.OK);
	       } catch (Exception e) {
	           throw  new InternalError("Something went wrong");
	       }
	   }

	
}