package hiringProcess.web.controllerAdvice;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hiringProcess.exception.UserNotFoundException;
import hiringProcess.util.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ TransactionSystemException.class, DataIntegrityViolationException.class })
	protected ResponseEntity<Object> handleConstraintViolationExceptions(Exception ex,
			WebRequest request) {

		ex.printStackTrace();

		ApiError apiError = new ApiError();
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setError(ex.getCause().getLocalizedMessage());
		apiError.setPath(request.getDescription(true).split(";")[0].split("=")[1]);

		if (ex.getClass().equals(TransactionSystemException.class)) {

			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			Set<ConstraintViolation<?>> constraintViolations = null;

			if (cause instanceof ConstraintViolationException) {

				constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
				String errorMessage = null;

				for (Iterator<ConstraintViolation<?>> it = constraintViolations.iterator(); it.hasNext();) {
					ConstraintViolation<?> cv = it.next();
					errorMessage = cv.getMessage();
				}

				apiError.setMessage(errorMessage);

			} else {

				apiError.setMessage(ex.getMessage());
			}
		}

		else if (ex.getClass().equals(DataIntegrityViolationException.class)) {
			apiError
					.setMessage(((DataIntegrityViolationException) ex).getRootCause().getLocalizedMessage());
		}

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<Object> hanlderCustomExceptions(Exception ex, WebRequest request) {

		ex.printStackTrace();

		ApiError apiError = new ApiError();

		if (ex.getClass().equals(UserNotFoundException.class))
			apiError.setStatus(HttpStatus.NOT_FOUND);

		else
			apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

		apiError.setError(ex.getClass().getSuperclass().getSimpleName());
		apiError.setPath(request.getDescription(true).split(";")[0].split("=")[1]);
		apiError.setMessage(ex.getMessage());

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// @ExceptionHandler(DataIntegrityViolationException.class)
	// protected ResponseEntity<Object> handleSQLIntegrityConstraintViolation(
	// DataIntegrityViolationException ex, WebRequest request) {
	//
	// ApiError apiError = new ApiError();
	// apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
	// apiError.setErrorStatus(HttpStatus.BAD_REQUEST);
	// apiError.setPath(request.getDescription(true).split(";")[0].split("=")[1]);
	//
	// String errorMessage = ex.getRootCause().toString();
	// apiError.setMessage(errorMessage);
	// return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getErrorStatus());
	// }
	//
	// @ExceptionHandler(Exception.class)
	// protected ResponseEntity<Object> handleExceptions(Exception ex) {
	// ex.printStackTrace();
	// Map<String, String> errMessages = new HashMap<>();
	// errMessages.put("message", ex.getLocalizedMessage());
	// ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
	// ex.getClass().getSimpleName() , errMessages);
	// return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	// }

}
