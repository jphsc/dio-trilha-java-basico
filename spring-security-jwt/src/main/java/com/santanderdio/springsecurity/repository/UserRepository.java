package com.santanderdio.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santanderdio.springsecurity.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username = (:username) ")
	public User findByUsername(@Param("username") String username);
	
	public boolean existsByUsername(String username);
}
