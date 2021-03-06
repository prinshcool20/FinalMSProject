package com.alfar.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alfar.spring.model.Authuserdetail;
import com.alfar.spring.model.User;
import com.alfar.spring.repository.UserRepository;



@Service("userDetailsService")
public class UserdetailServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository repo; 

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
	
		Optional<User> optionalUser = repo.findByUsername(name);
		
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("username or password error"));
		
		UserDetails userdetails = new Authuserdetail(optionalUser.get());
		
		new AccountStatusUserDetailsChecker().check(userdetails);
		
		return userdetails;
		
	}

}
