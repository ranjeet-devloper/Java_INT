package com.jwt.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.jwt.model.JwtResponse;
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMetodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String,String> rep=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			rep.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(rep, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<JwtResponse> handleException(UserException ex)
	{
		JwtResponse obj=new JwtResponse(401, ex.getMessage(),"");
		return new ResponseEntity<JwtResponse>(obj,HttpStatus.BAD_REQUEST);
	}
}
