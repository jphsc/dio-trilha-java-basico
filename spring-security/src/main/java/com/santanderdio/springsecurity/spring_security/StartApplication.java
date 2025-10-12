package com.santanderdio.springsecurity.spring_security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.santanderdio.springsecurity.spring_security.entity.User;
import com.santanderdio.springsecurity.spring_security.repository.UserRepository;

@Component
public class StartApplication implements CommandLineRunner {

	@Autowired UserRepository repository;
	@Override
	public void run(String... args) throws Exception {
		
		List<User> users = repository.findAll();
		users.forEach(System.out::println);
		
		User user = repository.findByUsername("admin");
		
		if(user == null) {
			user = new User.Builder().setName("ADMIN")
					.setUsername("admin")
					.setPassword("master123")
					.build();
			user.getRoles().add("ROLE_ADMIN");
			repository.save(user);
		}
		
		user = repository.findByUsername("user");
		
		if(user == null) {
			user = new User.Builder().setName("USER")
					.setUsername("user")
					.setPassword("user123")
					.build();
			user.getRoles().add("ROLE_USER");
			repository.save(user);
		}
	}

}
