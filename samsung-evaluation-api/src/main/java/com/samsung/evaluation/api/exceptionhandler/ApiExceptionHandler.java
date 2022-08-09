package com.samsung.evaluation.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.samsung.evaluation.api.exceptionhandler.Problem.Field;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Field> fields = new ArrayList<Problem.Field>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			
			fields.add(new Problem.Field(name, message));
		}
		
		return super.handleExceptionInternal(ex, 
						new Problem(status.value(), 
							LocalDateTime.now(), 
							"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", 
							fields), 
						headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		
		List<Field> fields = new ArrayList<Problem.Field>();
		
		String name = ex.getPropertyName();
		String message = ex.getMessage();
			
		fields.add(new Problem.Field(name, message));
		
		
		return super.handleExceptionInternal(ex, 
				new Problem(status.value(), 
					LocalDateTime.now(), 
					"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", 
					fields), 
				headers, status, request);
	}
	
	
}
