package com.security.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
	
	@Autowired
	private User byUserName;
	
	public UserPrincipal(User user) {
		this.byUserName=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER")); // returnign only one roll for that we have to use single ton
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return byUserName.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return byUserName.getUsername();
	}

}
