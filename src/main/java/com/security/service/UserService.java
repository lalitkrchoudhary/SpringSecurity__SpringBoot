package com.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.security.model.User;
import com.security.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	AuthenticationManager authMangaer;
	
	@Autowired
	private JWTService jwtService;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	
	
	public List<User > student(){
		List<User> all = repo.findAll();
		System.out.println(all);
		return all;
		
	}
	
	public User register(User user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		User save = repo.save(user);
		
		return save;
	}
	
	public String verify(User user) {
		
		
		 Authentication authenticate = authMangaer.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		 
		 if(authenticate.isAuthenticated()) {
			 return jwtService.generateToken(user.getUsername());
		 }
		 
		 return "fail";
		 
		 
		 
//		  User byUsername = repo.findByUsername(user.getUsername());
//		String result;
//		
//		if(byUsername!=null) {
//			result="find with the user name "+byUsername.getUsername();
//		}else {
//			result ="not found any user "+user.getUsername() ;
//		}
//		return  result;
	}
}
