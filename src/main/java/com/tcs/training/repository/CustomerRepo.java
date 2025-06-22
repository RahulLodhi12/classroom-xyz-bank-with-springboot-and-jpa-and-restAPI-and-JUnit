package com.tcs.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.training.bean.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
