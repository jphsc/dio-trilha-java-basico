package com.santanderdio.springsecurity.spring_security.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.santanderdio.springsecurity.spring_security.entity.User;
import com.santanderdio.springsecurity.spring_security.repository.UserRepository;

@Service
public class SecurityDatabaseService implements UserDetailsService{

	@Autowired UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		userEntity.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role)));
		
		UserDetails user = new org.springframework.security.core.userdetails.User(username, userEntity.getPassword(), authorities);
		
		return user;
	}

}
