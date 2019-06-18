package controller;

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

import exception.UserNotFoundException;
import model.User;
import service.UserService;


/**
 * 
 * User Controller - manages the creation, deletion and updating of users
 * @author Giovana Brito Oliveria
 *
 */
@RestController
@RequestMapping({ "/v1/users" })
public class UserController {

	private UserService userService;

	UserController(UserService userService) {
		this.userService = userService;
	}

	// This method finds an user through his email
	@GetMapping(value = "/{email}")
	@ResponseBody
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
		User user = userService.findByEmail(email);

		if (user == null) {
			throw new UserNotFoundException("User not found");
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// User creation method
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<User> create(@RequestBody User user) {
		User newUser = userService.create(user);

		if (newUser == null) {
			throw new InternalError("Something went wrong");
		}
		
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	
	//User deletion method
	@DeleteMapping(value = "/{email}")
	   public ResponseEntity delete(@PathVariable String email) {
	       try {
	           userService.delete(email);
	           return new ResponseEntity(HttpStatus.OK);
	       } catch (Exception e) {
	           throw new InternalError("Something went wrong");
	       }
	   }
	
	//User updating method
	@PutMapping(value = "/")
	   public ResponseEntity<User> update(@RequestBody User user) {
	       try {
	           User updated = userService.update(user);
	           return new ResponseEntity<>(updated, HttpStatus.OK);
	       } catch (Exception e) {
	           throw  new InternalError("Something went wrong");
	       }
	   }

}