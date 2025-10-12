package com.santanderdio.springrestapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santanderdio.springrestapi.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping // http://localhost:8999/user >> METODO POST
	public void insertUser(@RequestBody User user) {
		// salvar um usuário na base
		System.out.println("post /user com corpo da requisicao: "+user);
	}
	
	@GetMapping // http://localhost:8999/user?id=xxx >> METODO GET
	public User getUserById(@RequestParam("id") Integer id) {
		System.out.println("get /user/id=xx id = "+id);
		return new User(); // retornar um user pelo id
	}
	
	@DeleteMapping("/{id}") // http://localhost:8999/user/xxx >> METODO DELETE
	public void deleteUserById(@PathVariable("id") Integer id) {
		System.out.println("delete /user/xx id = "+id);
		// retornar um user pelo id
	}
	
	@PutMapping // http://localhost:8999/user >> METODO PUT
	public void updateUser(@RequestBody User user) {
		System.out.println("update /user com corpo da requisicao: " + user);
		// atualizar um user
	}
}
