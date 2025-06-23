package com.tcs.training.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login {
	@Id
	private String accountNumber;
	private String pin;
	private Double balance;
	private String roles;
	
	public Login(String accNo, String pin2) {
		// TODO Auto-generated constructor stub
		this.accountNumber = accNo;
		this.pin = pin2;
	}

//	public Login(long long1, String string) {
//		// TODO Auto-generated constructor stub
//	}

	public Login() {
		
	}
	
	public Login(String accountNumber, String pin, Double balance) {
		super();
		accountNumber = accountNumber;
		pin = pin;
		balance = balance;
	}

	public Login(String accountNumber, String pin, Double balance, String roles) {
	super();
	accountNumber = accountNumber;
	pin = pin;
	balance = balance;
	this.roles = roles;
}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		accountNumber = accountNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		pin = pin;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		balance = balance;
	}
	
	
	
}
