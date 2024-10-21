package com.akeir.publisher.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akeir.publisher.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);

}
