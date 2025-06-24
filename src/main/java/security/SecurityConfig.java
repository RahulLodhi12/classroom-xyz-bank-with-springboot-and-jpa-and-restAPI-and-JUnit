//package com.tcs.training.security;
//
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.crypto.password.NoOpPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//    MyUserDetailsService uuserDetailsService;
//	
////	For customizing authentication:
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(uuserDetailsService);
//	}
//	
////	For customizing authorization:
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//        .antMatchers("/xyz/bank/customers").hasRole("ADMIN")
//        .antMatchers("/xyz/bank/customers/{accNo}").hasRole("USER")
//        .antMatchers("/xyz/bank/").permitAll()
//        .and()
//        .formLogin();	
//	}
//	
//	@Bean
//	PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//	
//}
package security;


