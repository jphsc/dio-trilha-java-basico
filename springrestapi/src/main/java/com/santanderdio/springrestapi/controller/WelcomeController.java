package com.santanderdio.springrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santanderdio.springrestapi.model.User;

@RestController // necessário para informar que é uma classe de controller > fornecedora de recursos
public class WelcomeController {

	@GetMapping // necessário informar o tipo de método que acionará esse recurso
	public String welcome1() {
		return "Welcome to my Spring Boot Web API - /";
	}
	

	@GetMapping("/teste") // name = "caminho" == sem (), é o path dentro do recurso
	public String welcome2() {
		
		User user1 = new User(1, "X", "X");
		System.out.println(user1);
		return "Welcome to my Spring Boot Web API - /teste";
		
	}
}
