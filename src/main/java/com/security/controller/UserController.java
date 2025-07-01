package com.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.model.User;
import com.security.service.UserService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String welcome(HttpServletRequest request) {
		return "welcome to the security" + " and the session id is : "+request.getSession().getId();
	}
	@GetMapping("/students")
	public List<User> users() {
		
		return service.student();
	}
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		
		User reg = service.register(user);
		
		return reg;
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		System.out.println(user);
		return service.verify(user);
	}

}
