package com.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.service.JWTService;
import com.security.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	ApplicationContext context;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Bearer 
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			username= jwtService.extractUserName(token);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails= context.getBean(MyUserDetailsService.class).loadUserByUsername(username); 
			if(jwtService.validateToken(token,userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
			}
		}
			
		
	}

}
