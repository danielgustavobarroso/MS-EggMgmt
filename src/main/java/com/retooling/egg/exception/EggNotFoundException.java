package com.retooling.egg.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EggNotFoundException extends Exception {

	public EggNotFoundException() {
		super();
	}
	
	public EggNotFoundException(String message) {
		super(message);
	}
	
}
