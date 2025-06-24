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
import com.tcs.training.exception.CreateAccountException;
import com.tcs.training.exception.CustomerNotFoundException;
import com.tcs.training.exception.DeleteCustomerException;
import com.tcs.training.exception.DepositException;
import com.tcs.training.exception.UpdateCustomerException;
import com.tcs.training.exception.WithdrawException;
import com.tcs.training.service.ServiceClass;

@RestController
@RequestMapping("xyz/bank")
public class ControllerClass {

	@Autowired
	ServiceClass service;
	
	@GetMapping("/")
	public String publicMsg() {
		return "Welcome to XYZ Bank";
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomer() {
		return service.getAllCustomer();
	}
	
	
	@PostMapping("/createAccount")
	public boolean createAccount(@RequestParam int accNo, @RequestParam String name, @RequestParam String branch, @RequestParam Double balance) throws SQLException, CreateAccountException {
		boolean res = service.createAccount(accNo,name,branch,balance);
		if(res==false) {
			throw new CreateAccountException();
		}
		return res;
	}
	
	
	@GetMapping("/customers/{accNo}")
	public Optional<Customer> getCustomerByAccountNumber(@PathVariable int accNo) throws CustomerNotFoundException {
		Optional<Customer> customer = service.getCustomerByAccountNumber(accNo);
		if(customer.isEmpty()) {
			throw new CustomerNotFoundException();
		}
		
		return customer;
	}
	
	@PutMapping("/customers/{accNo}")
	public boolean updateCustomerByAccountNumber(@PathVariable int accNo,@RequestBody Customer customer) throws UpdateCustomerException {
		boolean res = service.updateCustomerByAccountNumber(accNo,customer);
		if(res==false) {
			throw new UpdateCustomerException();
		}
		return res;
	}
	
	@DeleteMapping("/customers/{accNo}")
	public boolean deleteCustomerByAccountNumber(@PathVariable int accNo) throws DeleteCustomerException {
		boolean res = service.deleteCustomerByAccountNumber(accNo);
		if(res==false) {
			throw new DeleteCustomerException();
		}
		return res;
	}
	
	@PostMapping("/customers/{accNo}/deposit")
	public boolean depositMoneyByAccountNumber(@PathVariable int accNo, @RequestParam int amt) throws DepositException {
		boolean res = service.depositMoneyByAccountNumber(accNo,amt);
		if(res==false) {
			throw new DepositException();
		}
		return res;
	}
	
	@PostMapping("/customers/{accNo}/withdraw")
	public boolean withdrawMoneyByAccountNumber(@PathVariable int accNo, @RequestParam int amt) throws WithdrawException {
		boolean res = service.withdrawMoneyByAccountNumber(accNo,amt);
		if(res==false) {
			throw new WithdrawException();
		}
		return res;
	}
	
	@PostMapping("/customers/{accNo1}/fundTransfer")
	public boolean fundTransferByAccountNumber(@PathVariable int accNo1, @RequestParam int amt, @RequestParam int accNo2) {
		return service.fundTransferByAccountNumber(accNo1,amt,accNo2);
	}
	
}
