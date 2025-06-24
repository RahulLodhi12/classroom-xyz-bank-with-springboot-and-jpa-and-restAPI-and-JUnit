package com.tcs.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionController {
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Object> exception(CustomerNotFoundException exception){
		
		return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = DepositException.class)
	public ResponseEntity<Object> exception(DepositException exception){
		return new ResponseEntity<>("Deposit Fail..", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = WithdrawException.class)
	public ResponseEntity<Object> exception(WithdrawException exception){
		return new ResponseEntity<>("Withdraw Fail..", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = CreateAccountException.class)
	public ResponseEntity<Object> exception(CreateAccountException exception){
		return new ResponseEntity<>("Can't Create Account..", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UpdateCustomerException.class)
	public ResponseEntity<Object> exception(UpdateCustomerException exception){
		return new ResponseEntity<>("Update Error..", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = DeleteCustomerException.class)
	public ResponseEntity<Object> exception(DeleteCustomerException exception){
		return new ResponseEntity<>("Delete Error..", HttpStatus.NOT_FOUND);
	}
}
