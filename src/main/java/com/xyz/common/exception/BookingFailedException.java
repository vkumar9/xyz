package com.xyz.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class BookingFailedException extends RuntimeException {
	
	 public BookingFailedException(String reason ) {
	         super(reason);
	    }

}
