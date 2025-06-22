package com.tcs.training.controller;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.training.bean.Customer;
import com.tcs.training.bean.Login;
import com.tcs.training.service.ServiceClass;

@RestController
@RequestMapping("xyz/bank")
public class ControllerClass {

	@Autowired
	ServiceClass service;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomer() {
		return service.getAllCustomer();
	}
	
	
	@PostMapping("/createAccount")
	public void createAccount(@RequestParam int accNo, @RequestParam String name, @RequestParam String branch, @RequestParam Double balance) throws SQLException {
		service.createAccount(accNo,name,branch,balance);
	}
	
	
	@GetMapping("/customers/{accNo}")
	public Optional<Customer> getCustomerByAccountNumber(@PathVariable int accNo) {
		return service.getCustomerByAccountNumber(accNo);
	}
	
	@PutMapping("/customers/{accNo}")
	public void updateCustomerByAccountNumber(@PathVariable int accNo,@RequestBody Customer customer) {
		service.updateCustomerByAccountNumber(accNo,customer);
	}
	
	@DeleteMapping("/customers/{accNo}")
	public void deleteCustomerByAccountNumber(@PathVariable int accNo) {
		service.deleteCustomerByAccountNumber(accNo);
	}
	
	@PostMapping("/customers/{accNo}/deposit")
	public void depositMoneyByAccountNumber(@PathVariable int accNo, @RequestParam int amt) {
		service.depositMoneyByAccountNumber(accNo,amt);
	}
	
	@PostMapping("/customers/{accNo}/withdraw")
	public void withdrawMoneyByAccountNumber(@PathVariable int accNo, @RequestParam int amt) {
		service.withdrawMoneyByAccountNumber(accNo,amt);
	}
	
	@PostMapping("/customers/{accNo1}/fundTransfer")
	public void fundTransferByAccountNumber(@PathVariable int accNo1, @RequestParam int amt, @RequestParam int accNo2) {
		service.fundTransferByAccountNumber(accNo1,amt,accNo2);
	}
	
}
