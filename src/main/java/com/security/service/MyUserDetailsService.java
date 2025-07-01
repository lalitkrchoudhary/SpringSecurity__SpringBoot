package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.model.User;
import com.security.model.UserPrincipal;
import com.security.repo.UserRepo;
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User byUsername = repo.findByUsername(username);
		if(byUsername==null) {
			System.out.println("user is not found ");
			throw new UsernameNotFoundException("USER NOT FOUND");
		}else {
			System.out.println("user found successfully");
			System.out.println(byUsername);
		}
		return new UserPrincipal(byUsername);
	}

}
