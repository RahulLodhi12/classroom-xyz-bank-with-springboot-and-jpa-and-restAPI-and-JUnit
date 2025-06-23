package com.tcs.training.bean;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="customer")
public class Customer {
	@Id
	private int accNo;
	private String name;
	private String branch;
	private double balance;
	

	public Customer() {
		
	}
	
	public Customer(int accNo) {
		// TODO Auto-generated constructor stub
		this.accNo = accNo;
	}
	
	public Customer(int accNo, String name, String branch) {
		this.accNo = accNo;
		this.name = name;
		this.branch = branch;
	}

	public Customer(double balance) {
		this.balance = balance;
	}

	public Customer(int accNo, String name, String branch, double balance) {
		int timestamp = (int) System.currentTimeMillis() % 10000;
		int randomPart = 10000 + new Random().nextInt(90000);
		accNo = randomPart * 10000 + timestamp;
		
		this.accNo = accNo;
		this.name = name;
		this.branch = branch;
		this.balance = balance;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	
	
}
