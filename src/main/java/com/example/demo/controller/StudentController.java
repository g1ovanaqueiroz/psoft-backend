package com.example.demo.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.demo.model.Login;
import com.example.demo.model.Student;
import com.example.demo.model.StudentDTO;
import com.example.demo.service.StudentService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * User Controller - manages the creation, deletion and updating of users
 * 
 * @author Giovana Brito Oliveria
 *
 */
@Api
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/students")
public class StudentController {

	private StudentService studentService;

	/**
	 * StudentController constructor
	 * 
	 * @param studentService StudentService
	 */
	StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * Returns the token of an existing user
	 * 
	 * @param login user email and password
	 * @return StudentDTO
	 */
	@ApiOperation(value = "Returns the token of an existing user")
	@CrossOrigin
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<StudentDTO> login(@RequestBody Login login) {
		Student newUser = studentService.findByLogin(login.getEmail(), login.getPassword());
		final String TOKEN_KEY = "magicword";

		if (newUser == null) {
			throw new StudentNotFoundException("Usuario nao encontrado!");
		}

		if (!newUser.getPassword().equals(login.getPassword())) {
			throw new InvalidPasswordException("Senha invalida!");
		}

		String token = Jwts.builder().setSubject(newUser.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 1800000)).compact();

		StudentDTO userDTO = new StudentDTO(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), token);
		return new ResponseEntity<StudentDTO>(userDTO, HttpStatus.FOUND);
	}

	/**
	 * Student creation method
	 * 
	 * @param student Student
	 * @return new StudentDTO
	 */
	@ApiOperation(value = "Creates a students on database")
	@CrossOrigin
	@PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<StudentDTO> singup(@RequestBody Student student) {
		System.out.println(student.getEmail());
		Student newStudent = studentService.create(student);
		final String TOKEN_KEY = "magicword";

		if (newStudent == null) {
			throw new InternalError("Something went wrong");
		}

		String token = Jwts.builder().setSubject(newStudent.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 1800000)).compact();

		StudentDTO studentDTO = new StudentDTO(student.getFirstName(), student.getLastName(), student.getEmail(),
				token);

		return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.CREATED);
	}

	/**
	 * Student deletion method
	 * 
	 * @param email user email
	 * @return HttpStatus
	 */
	@ApiOperation(value = "Deletes a Student from database")
	@CrossOrigin
	@DeleteMapping(value = "/delete/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity delete(@PathVariable String email) {
		try {
			studentService.delete(email);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * Student updating method
	 * 
	 * @param student User to update
	 * @return StudentDTO
	 */
	@ApiOperation(value = "Updates a Student from database")
	@CrossOrigin
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentDTO> update(@RequestBody Student student) {
		try {
			Student updated = studentService.update(student);

			String token = Jwts.builder().setSubject(updated.getEmail())
					.signWith(SignatureAlgorithm.HS512, "secretword")
					.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).compact();

			StudentDTO userDTO = new StudentDTO(updated.getFirstName(), updated.getLastName(), updated.getEmail(),
					token);
			return new ResponseEntity<StudentDTO>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

}