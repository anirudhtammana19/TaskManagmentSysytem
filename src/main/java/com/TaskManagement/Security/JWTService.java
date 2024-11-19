package com.TaskManagement.Security;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
private static final String SECRET="C239DD322A05B809225CAB5D57A72FBB5564428F15CF882C37A209995BC1C06F3513F1E69B28C0105A27187ACF41B59C3517C7DD22438811A13B01B97453408B";
	
	private static final long Validity=TimeUnit.MINUTES.toMillis(30);
	
	
	public String generateToken(UserDetails userDetails) {
		String token= Jwts.builder()
		.setSubject(userDetails.getUsername())
		.setIssuedAt(Date.from(Instant.now()))
		.setExpiration(Date.from(Instant.now().plusMillis(Validity)))
		.signWith(getKey())
		.compact();
		//System.out.println("Generated JWT Token: " + token);
		return token;
	}
	
	private SecretKey getKey() {
		byte[] decodedKey=Base64.getDecoder().decode(SECRET);
		return Keys.hmacShaKeyFor(decodedKey);
		
	}

	public String extractUsername(String jwt) {
		Claims claims= getClaims(jwt);
		return claims.getSubject();
		
	}
	
	private Claims getClaims(String jwt) {
		return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
	}
	public boolean isTokenValid(String jwt) {
		Claims claims=getClaims(jwt);
		return claims.getExpiration().after(Date.from(Instant.now()));
	}

}
