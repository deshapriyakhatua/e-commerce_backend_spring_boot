package com.spring.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.spring.rest.model.MyExceptionDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<MyExceptionDetails> myExceptionHandler(
			InvalidCredentialsException invalidCredentialsException, WebRequest req) {

		MyExceptionDetails myExceptionDetails = new MyExceptionDetails(invalidCredentialsException.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<MyExceptionDetails>(myExceptionDetails, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyExceptionDetails> myExceptionHandler(Exception exception, WebRequest req) {

		MyExceptionDetails myExceptionDetails = new MyExceptionDetails(exception.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<MyExceptionDetails>(myExceptionDetails, HttpStatus.BAD_REQUEST);

	}
}
