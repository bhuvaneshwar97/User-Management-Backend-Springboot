package com.userservice.userservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DetailsNotFound extends RuntimeException{
	public DetailsNotFound(String message) {
		super(message);
	}
}