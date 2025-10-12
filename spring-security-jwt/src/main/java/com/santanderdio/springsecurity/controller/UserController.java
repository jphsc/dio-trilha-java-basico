package com.santanderdio.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santanderdio.springsecurity.UserService;
import com.santanderdio.springsecurity.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired private UserService service;
	
	@PostMapping
	public void postUser(@RequestBody User user) {
		service.createUser(user);
	}
}
