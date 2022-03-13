package hiringProcess.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JWTverifyTokenException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JWTverifyTokenException(final String message) {
		super(message);
    }
}
