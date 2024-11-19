package com.TaskManagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.TaskManagement.DTO.UserDTO;
import com.TaskManagement.Security.JWTService;

@Service
public class AuthService {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	UserInfoUserDetailsService userInfoUserDetailsService;

	public String authenticateUser(UserDTO user) {
		Authentication authentication = authenticationManager.authenticate
				(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(userInfoUserDetailsService.loadUserByUsername(user.getUsername()));
			
		}
		else {
			throw new UsernameNotFoundException("Username not found");
		}
		
	}
	
	

}
