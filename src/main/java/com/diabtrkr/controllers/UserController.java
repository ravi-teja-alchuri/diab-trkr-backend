package com.diabtrkr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diabtrkr.models.User;
import com.diabtrkr.request.dtos.LoginDTO;
import com.diabtrkr.request.dtos.UserDTO;
import com.diabtrkr.response.dtos.LoginResponse;
import com.diabtrkr.services.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody UserDTO dto) {
		User user = service.create(dto);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/hello")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hi! How are you doing?");
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO dto) {
		LoginResponse login = service.login(dto);
		return ResponseEntity.ok(login);
	}

}
