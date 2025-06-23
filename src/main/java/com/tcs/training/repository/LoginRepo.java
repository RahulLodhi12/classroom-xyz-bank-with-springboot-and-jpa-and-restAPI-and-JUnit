package com.tcs.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.training.bean.Login;

public interface LoginRepo extends JpaRepository<Login, String> {

}
