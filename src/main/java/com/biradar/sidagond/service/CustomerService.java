package com.biradar.sidagond.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface CustomerService {

	ResponseEntity<String> signUp(Map<String, String> requestMap);

}
