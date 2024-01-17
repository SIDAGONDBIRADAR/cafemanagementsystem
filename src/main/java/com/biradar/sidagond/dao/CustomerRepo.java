package com.biradar.sidagond.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biradar.sidagond.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	Customer findByEmail(String email);

}
