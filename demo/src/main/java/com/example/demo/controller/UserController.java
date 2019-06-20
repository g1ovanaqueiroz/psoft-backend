package com.example.demo.controller;

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

import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.service.UserService;


/**
 * 
 * User Controller - manages the creation, deletion and updating of users
 * @author Giovana Brito Oliveria
 *
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {

	private UserService userService;

	UserController(UserService userService) {
		this.userService = userService;
	}
	
	//This method returns the token of an existing user
	@PostMapping(value = "/login")
	@ResponseBody
	public ResponseEntity<UserDTO> login(@RequestBody String email) {
		User newUser = userService.findByEmail(email);
		
		if (newUser == null) {
			throw new UserNotFoundException("User not found!");
		}
		
		UserDTO userDTO = new UserDTO(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), "123");
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.FOUND);
	}

	// User creation method
	@PostMapping(value = "/singup")
	@ResponseBody
	public ResponseEntity<UserDTO> singup(@RequestBody User user) {
		User newUser = userService.create(user);

		if (newUser == null) {
			throw new InternalError("Something went wrong");
		}
		
		UserDTO userDTO = new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), "123");
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
	}
	
	//User deletion method
	@DeleteMapping(value = "/delete")
	   public ResponseEntity delete(@PathVariable String email) {
	       try {
	           userService.delete(email);
	           return new ResponseEntity(HttpStatus.OK);
	       } catch (Exception e) {
	           throw new InternalError("Something went wrong");
	       }
	   }
	
	//User updating method
	@PutMapping(value = "/update")
	   public ResponseEntity<User> update(@RequestBody User user) {
	       try {
	           User updated = userService.update(user);
	           return new ResponseEntity<>(updated, HttpStatus.OK);
	       } catch (Exception e) {
	           throw  new InternalError("Something went wrong");
	       }
	   }

}