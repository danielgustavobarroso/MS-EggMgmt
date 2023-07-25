package com.retooling.egg.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EggValidationErrorException extends Exception {

	public EggValidationErrorException() {
		super();
	}
	
	public EggValidationErrorException(String message) {
		super(message);
	}
	
}
