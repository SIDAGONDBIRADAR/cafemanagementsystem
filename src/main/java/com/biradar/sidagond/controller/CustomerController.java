package com.biradar.sidagond.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biradar.sidagond.constants.CafeConstants;
import com.biradar.sidagond.service.CustomerService;
import com.biradar.sidagond.utils.CafeUtils;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap) {
		try {
			return customerService.signUp(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
