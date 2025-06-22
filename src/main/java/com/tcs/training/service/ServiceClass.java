package com.tcs.training.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.training.bean.Customer;
import com.tcs.training.bean.Login;
import com.tcs.training.bean.Transaction;
import com.tcs.training.repository.CustomerRepo;
import com.tcs.training.repository.TransactionRepo;

@Service
public class ServiceClass{
	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	TransactionRepo transactionRepo;

	public List<Customer> getAllCustomer() {
		return customerRepo.findAll();
	}

	public Optional<Customer> getCustomerByAccountNumber(int accNo) {
		return customerRepo.findById(accNo);
	}

	public boolean updateCustomerByAccountNumber(int accNo, Customer customer) {
		Optional<Customer> exist = customerRepo.findById(accNo);
        if(exist.isPresent()){
            customer.setAccNo(accNo); //setting the accountNumber, so we don't need to pass the accountNumber from user or postman.
            customerRepo.save(customer);
            return true;
        }
        else{
            System.out.println("Not Found..");
            return false;
        }
//		dao.updateCustomerByAccountNumber(accNo,customer);
	}

	public boolean deleteCustomerByAccountNumber(int accNo) {
		Optional<Customer> exist = customerRepo.findById(accNo);
        if(exist.isPresent()){
            customerRepo.delete(exist.get());
            return true;
        }
        else{
            System.out.println("Not Found..");
            return false;
        }
//		dao.deleteCustomerByAccountNumber(accNo);
	}

	public boolean depositMoneyByAccountNumber(int accNo, int amt) {
		Optional<Customer> exist = customerRepo.findById(accNo);
        if(exist.isPresent()){
            //operations on Customer Table
            Double currentBalance = exist.get().getBalance();
            exist.get().setBalance(currentBalance+amt);

            //operations on Transaction Table
            Transaction transaction = new Transaction(exist.get().getAccNo(),exist.get().getAccNo(),amt,null);
            transactionRepo.save(transaction);

            customerRepo.save(exist.get());
            return true;
        }
        else{
            System.out.println("Not Found..");
            return false;
        }
//		dao.depositMoneyByAccountNumber(accNo,amt);
	}

	public boolean withdrawMoneyByAccountNumber(int accNo, int amt) {
		Optional<Customer> exist = customerRepo.findById(accNo);
        if(exist.isPresent()){
            Double currentBalance = exist.get().getBalance();
            if(currentBalance-amt<1000){
                System.out.println("Minimum Balance should be 1000 after withdraw..");
                return false;
            }
            //Operations on Customer Table
            exist.get().setBalance(currentBalance-amt);

            //Operations on Transaction Table
            Transaction transaction = new Transaction(exist.get().getAccNo(),exist.get().getAccNo(),amt,null);
            transactionRepo.save(transaction);

            //Update/Save Customer Details
            customerRepo.save(exist.get());
            return true;
        }
        else{
            System.out.println("Not Found..");
            return false;
        }
//		dao.withdrawMoneyByAccountNumber(accNo,amt);
	}

	public boolean fundTransferByAccountNumber(int accNo1, int amt, int accNo2) {
		Optional<Customer> customer1 = customerRepo.findById(accNo1);
        Optional<Customer> customer2 = customerRepo.findById(accNo2);
        if(customer1.isPresent() && customer2.isPresent()){
            //Withdraw from Customer1
            Double currentBalance1 = customer1.get().getBalance();
            if(currentBalance1-amt<1000){
                System.out.println("Minimum Balance should be 1000 after withdraw..");
                return false;
            }
            customer1.get().setBalance(currentBalance1-amt);

            //Deposit to Customer2
            Double currentBalance2 = customer2.get().getBalance();
            customer2.get().setBalance(currentBalance2+amt);

            //Update Transaction Table
            Transaction transaction = new Transaction(customer1.get().getAccNo(),customer2.get().getAccNo(),amt,null);
            transactionRepo.save(transaction);

            //Update Both Customer Details
            customerRepo.save(customer1.get());
            customerRepo.save(customer2.get());
            return true;
        }
        else{
            System.out.println("Not Found..");
            return false;
        }
//		dao.fundTransferByAccountNumber(accNo1,amt,accNo2);
	}


	public boolean createAccount(int accNo, String name, String branch, Double balance) {
		Customer customer = new Customer(99,name,branch,balance);
        if(name.length()==0 || branch.length()==0) {
            return false;
        }
        else {
            customerRepo.save(customer);
            return true;
        }
//		dao.createAccount(accNo,name,branch,balance);
	}

//	public Customer getCustomerByAccountNumber(int accNo) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
}
