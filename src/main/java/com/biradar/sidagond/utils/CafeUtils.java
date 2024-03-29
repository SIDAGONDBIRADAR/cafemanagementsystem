package com.biradar.sidagond.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {

	private CafeUtils() {

	}

	public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatusCode) {
		return new ResponseEntity<String>(message, httpStatusCode);
	}

}
