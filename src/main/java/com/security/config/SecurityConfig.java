package com.security.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(customizer->customizer.disable());
		http.authorizeHttpRequests(request->request
				.requestMatchers("register","login")
				.permitAll()
				.anyRequest().authenticated()); // all are authenticated by doing this
		//http.formLogin(Customizer.withDefaults()); // this is used for form login in view page
		http.httpBasic(Customizer.withDefaults()); // this is used for postman other wise it will return index form
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	} 

	
	
@Bean
public AuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider  provider = new DaoAuthenticationProvider();
	provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	provider.setUserDetailsService(userDetailsService);
	return provider;
}
	

@Bean
public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
	return config.getAuthenticationManager();
}
	//Hard code user details
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		 UserDetails user1 = User
//				 .withDefaultPasswordEncoder()
//				 .username("amar")
//				 .password("amar")
//				 .roles("USER")
//				 .build();
//		
//		 UserDetails user2 = User
//				 .withDefaultPasswordEncoder()
//				 .username("rahul")
//				 .password("rahul")
//				 .roles("ADMIN")
//				 .build();
//		return new InMemoryUserDetailsManager(user1,user2);
//	}
//	 
	
}

