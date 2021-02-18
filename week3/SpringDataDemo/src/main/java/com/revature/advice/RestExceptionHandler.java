package com.revature.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.revature.exceptions.UserNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private ResponseEntity<Object> buildResponseEntity(String message, HttpStatus status) {
		return ResponseEntity.status(status).body(message);
	}

	/*
	 * Intercepts exceptions that caused by Invalid JSON
	 * 
	 * Might want to send back a 4XX Series
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String error = "Malformed JSON Request";
		
		return buildResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	/*
	 * Intercepts exceptions caused by Hibernate Validation
	 * 
	 * Recall the javax.validation package
	 * With annotations such as @Length or @Pattern or @Email
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String error = "Request failed validation";
		
		return buildResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	/*
	 * The below handlers will intercept custom business logic exceptions/scenarios
	 */
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
		String error = "No User Found";
		
		return buildResponseEntity(error, HttpStatus.NOT_FOUND);
	}
}
