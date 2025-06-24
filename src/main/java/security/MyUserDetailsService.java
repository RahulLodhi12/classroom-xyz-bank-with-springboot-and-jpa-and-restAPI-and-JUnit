//package com.tcs.training.security;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.tcs.training.bean.Login;
//import com.tcs.training.repository.LoginRepo;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService{
//	@Autowired
//    LoginRepo loginRepo;
//	
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Login> login = loginRepo.findById(username);
//
//        login.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
//
//        return login.map(MyUserDetails::new).get();
//    }
//}
package security;


