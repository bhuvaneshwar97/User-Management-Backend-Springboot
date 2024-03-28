package com.userservice.userservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Exception extends RuntimeException{
	public Exception(String message) {
		super(message);
	}
}
