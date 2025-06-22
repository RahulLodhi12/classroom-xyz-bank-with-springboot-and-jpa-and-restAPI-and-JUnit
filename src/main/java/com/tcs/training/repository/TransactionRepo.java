package com.tcs.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.training.bean.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

}
