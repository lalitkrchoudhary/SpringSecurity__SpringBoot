package com.security.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.security.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	
	private String secretKey="";
	
	public JWTService() {
		try {
			KeyGenerator keyGen= KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secretKey=Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	

	public String generateToken(String username) {
		// TODO Auto-generated method stub
		
		Map<String, Object> claims= new HashMap<>();
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+60*60*10))  //10 minutes expiry
				.and()
				.signWith(getKey()).compact();
		//return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30";
	}

	private Key getKey() {
		// TODO Auto-generated method stub
		//converting the string into byte and converting into key
		byte[] keyBytes=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);	
	}


	public String extractUserName(String token) {
		
		return "";
	}

}
