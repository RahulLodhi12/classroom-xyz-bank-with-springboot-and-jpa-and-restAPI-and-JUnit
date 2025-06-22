package com.tcs.training.bean;

import java.sql.Timestamp;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
	private int tId;
	private int fromAccNo;
	private int toAccNo;
	private double amt;
	private Timestamp date;
	
	public Transaction() {
		
	}
	
//	public Transaction(int tId, int fromAccNo, int toAccNo, double amt, Timestamp date) {
//		super();
//		this.tId = tId;
//		this.fromAccNo = fromAccNo;
//		this.toAccNo = toAccNo;
//		this.amt = amt;
//		this.date = date;
//	}

	public Transaction(int accNo, int accNo2, double amt, Timestamp date) {
		// TODO Auto-generated constructor stub
		this.fromAccNo = accNo;
		this.toAccNo = accNo2;
		this.amt = amt;
		this.date = new Timestamp(System.currentTimeMillis());
	}

	public int getFromAccNo() {
		return fromAccNo;
	}

	public void setFromAccNo(int fromAccNo) {
		this.fromAccNo = fromAccNo;
	}

	public int getToAccNo() {
		return toAccNo;
	}

	public void setToAccNo(int toAccNo) {
		this.toAccNo = toAccNo;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
	
}
