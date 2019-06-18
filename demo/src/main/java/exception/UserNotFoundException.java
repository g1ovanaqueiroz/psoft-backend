package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This error will be thrown when the user is not found
 * @author Giovana Brito Oliveira
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
   
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
