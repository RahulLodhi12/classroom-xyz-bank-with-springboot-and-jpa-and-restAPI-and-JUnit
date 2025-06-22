package com.tcs.training.bean;


public class Login {
	private int AccountNumber;
	private String Pin;
	private Double Balance;
	
	public Login(int accNo, String pin2) {
		// TODO Auto-generated constructor stub
		this.AccountNumber = accNo;
		this.Pin = pin2;
	}

	public Login(long long1, String string) {
		// TODO Auto-generated constructor stub
	}

	public Login(int accountNumber, String pin, Double balance) {
		super();
		AccountNumber = accountNumber;
		Pin = pin;
		Balance = balance;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getPin() {
		return Pin;
	}

	public void setPin(String pin) {
		Pin = pin;
	}

	public Double getBalance() {
		return Balance;
	}

	public void setBalance(Double balance) {
		Balance = balance;
	}
	
	
	
}
