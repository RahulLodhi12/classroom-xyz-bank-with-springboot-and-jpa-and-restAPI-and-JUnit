//package com.tcs.training.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.tcs.training.bean.Login;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class MyUserDetails implements UserDetails {
//	private String userName, password;
//
////    boolean active;
//
//    List<SimpleGrantedAuthority> authorities;
//
//    MyUserDetails(Login login){
//    this.userName = login.getAccountNumber();
//
//    this.password = login.getPin();
//
////    this.active = user.isActive();
//
//    this.authorities = Arrays.stream(login.getRoles().split(","))
//            .map(SimpleGrantedAuthority::new)
//            .collect(Collectors.toList());
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//
//
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
////    @Override
////    public boolean isEnabled() {
////        return this.active;
////    }
//}
package security;


