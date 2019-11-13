package com.icia.testShop.advice;

import java.net.BindException;
import java.util.*;

import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class TestShopAdvice {
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/plain;charset=utf-8");
		return headers;
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> IllegalArgumentExceptionHandler() {
		return new ResponseEntity<String>("잘못된 요청입니다.", getHeaders(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<?> BindExceptionHandler(BindingResult results)	{
		List<Map<String,String>> errorMessage = new ArrayList<>();
		List<ObjectError> errors = results.getAllErrors();
		for(ObjectError error:errors) {
			FieldError err = (FieldError)error;
			Map<String,String> map = new HashMap<>();
			map.put(err.getField(), err.getDefaultMessage());
			errorMessage.add(map);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
	}
	
}
