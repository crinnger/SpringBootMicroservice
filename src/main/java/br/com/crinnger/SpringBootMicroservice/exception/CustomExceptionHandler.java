package br.com.crinnger.SpringBootMicroservice.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler { 
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e){
		List<String> erros = new ArrayList<>(e.getConstraintViolations().size());
		
		e.getConstraintViolations().forEach(constraintViolation-> {
			erros.add("erros" + constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
		});
	
		return new ResponseEntity<>(erros,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<List> handleBindException(BindException ex){
		return new ResponseEntity(ex.getAllErrors(),HttpStatus.BAD_REQUEST);
	}
}
