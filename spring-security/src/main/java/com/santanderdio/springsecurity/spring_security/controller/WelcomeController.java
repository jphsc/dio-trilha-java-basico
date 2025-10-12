package com.santanderdio.springsecurity.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

	@GetMapping
	public String welcome() {
		return "homepage";
	}
	
	@GetMapping("/user") // necessário informar o tipo de método que acionará esse recurso
	public String welcome1() {
		return "pg user";
	}
	

	@GetMapping("/admin") // name = "caminho" == sem (), é o path dentro do recurso
	public String welcome2() {
		return "pg admin";
		
	}
}
