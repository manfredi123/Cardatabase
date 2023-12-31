package com.packt.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtService {
	
	static final long EXPIRATIONTIME = 86400000; //Un giorno in ms
	static final String PREFIX = "Bearer"; //Prefisso per il token, solitamente si utilizza lo schema Bearer
	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //Chiave creata con JWT, solitamente la chiave si prende dalla configurazione
	
	//Genera e ritorna il token
	public String getToken(String username) {
		String token = Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).signWith(key).compact();
		
		return token;
	}
	
	//
	public String getAuthUser(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(token != null) {
			String user = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.replace(PREFIX, "")).getBody().getSubject();
		
		
		if(user != null)
			return user;
		}
		
		return null;
	}
}
