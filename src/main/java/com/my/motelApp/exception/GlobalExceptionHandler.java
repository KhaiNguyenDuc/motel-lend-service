package com.my.motelApp.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleDataNotFoundException(DataNotFoundException de, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),
				de.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.OK);
	}
}
