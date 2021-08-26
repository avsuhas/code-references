package com.codesamples.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
	
	protected static final String MALFORMED_JSON_REQUEST_BODY = "Malformed JSON request body";
	protected static final String CODE = "code";
	protected static final String MESSAGE = "message";
	
	
	//error handle for @Valid
	//exception handling for empty request body. Messages will be picked up from the model class where annotation @NotNull is used
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																HttpHeaders headers,
																HttpStatus status, WebRequest request){
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(CODE, status.value());
		
		//Get all errors
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		body.put(MESSAGE, StringUtils.join(errors, ','));
		return new ResponseEntity<>(body, headers, status);
		
	}
	
	//Exception handling for missing Request Body or Malformed json request body
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers,
			HttpStatus status, WebRequest request){
				
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(CODE, status.value());
		body.put(MESSAGE, MALFORMED_JSON_REQUEST_BODY);
		return new ResponseEntity<>(body, headers, status);
	}
}
