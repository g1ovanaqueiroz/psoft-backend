package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This error will be thrown when the password is invalid
 * @author Giovana Brito Oliveira
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidPasswordException extends RuntimeException {
   
	public InvalidPasswordException(String msg) {
		super(msg);
	}
}
