package com.my.motelApp.security;

import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwtSecret}")
	private String secretKey;
	
	@Value("${app.jwtExpiration}")
	private int jwtExpiration;
	
	public String generateToken(Authentication authentication) {
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		Date now = new Date();
		Date expiredDate = new Date(now.getTime()+jwtExpiration);
		
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		String jwt = Jwts.builder()
				.setSubject(userPrincipal.getId().toString())
				.setExpiration(expiredDate)
				.signWith(key)
				.setIssuedAt(now)
				.compact();
		return jwt;
	}
	
	public String getToken(HttpServletRequest request) {
		String bearer = request.getHeader("Authorization");
		if(StringUtils.hasText(bearer)&&bearer.contains("bearer")) {
			return bearer.substring(7,bearer.length());
		}
		return null;
	}
	
	public Boolean validateToken(String token) {
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			
			return true;
		}catch(Exception e) {
			return false;
		}
		
		
		
	}

	public Long getUserId(String token) {
		
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		try {
			Claims jwt = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
			return Long.valueOf(jwt.getSubject());
		}catch(Exception e) {
			return null;
		}
	}
}
