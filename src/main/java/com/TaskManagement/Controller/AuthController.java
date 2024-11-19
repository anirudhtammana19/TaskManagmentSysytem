package com.TaskManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.DTO.UserDTO;
import com.TaskManagement.Service.AuthService;



@RestController
@RequestMapping("/api")
public class AuthController {

	
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDTO user) {
		
		String login= authService.authenticateUser(user);
		return new ResponseEntity<>(login,HttpStatus.ACCEPTED);
	}
	
}
