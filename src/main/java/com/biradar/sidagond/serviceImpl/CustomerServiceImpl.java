package com.biradar.sidagond.serviceImpl;

import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biradar.sidagond.constants.CafeConstants;
import com.biradar.sidagond.dao.CustomerRepo;
import com.biradar.sidagond.model.Customer;
import com.biradar.sidagond.service.CustomerService;
import com.biradar.sidagond.utils.CafeUtils;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepository;

	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		logger.info("inside signUp method");
		try {
			if (validateSignUpMap(requestMap)) {
				Customer customer = customerRepository.findByEmail(requestMap.get("email"));
				if (Objects.isNull(customer)) {
					customerRepository.save(extractCustomerEntityfromMap(requestMap));
					return CafeUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
				} else {
					return CafeUtils.getResponseEntity("Email is already registered", HttpStatus.BAD_REQUEST);
				}
			} else {
				return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

	}

	private Boolean validateSignUpMap(Map<String, String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("password")
				&& requestMap.containsKey("contactNo")) {
			return true;
		} else {
			return false;
		}

	}

	private Customer extractCustomerEntityfromMap(Map<String, String> requestMap) {
		Customer customer = new Customer();
		customer.setName(requestMap.get("name"));
		customer.setEmail(requestMap.get("email"));
		customer.setContactNo(requestMap.get("contactNo"));
		customer.setRole("user");
		customer.setPassword(requestMap.get("password"));
		customer.setStatus("false");
		return customer;
	}

}
