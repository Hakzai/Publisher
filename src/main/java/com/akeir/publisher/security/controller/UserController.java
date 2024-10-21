package com.akeir.publisher.security.controller;

import java.util.InputMismatchException;
import java.util.List;

import javax.management.OperationsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.akeir.publisher.security.model.User;
import com.akeir.publisher.security.repository.UserRepository;
import com.akeir.publisher.security.service.PasswordService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/list")
	public List<User> list()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/find/{id}")
	public User findById(@PathVariable Integer id)
	{
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ENTITY NOT FOUND FOR ID " + id));
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) throws OperationsException
	{
		if(user.getUsername().isEmpty() || user.getUsername().length() < 5 || user.getPassword().isEmpty() || user.getPassword().length() < 8)
		{
			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
		}
		else if(user.getId() != null)
		{
			throw new OperationsException("WRONG OPERATION. USE EDIT FOR SAVING EXISTING ENTITY");
		}
		
		encryptPassword(user);
		return userRepository.save(user);
	}

	@PostMapping("/edit")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public User edit(@RequestBody User user)
	{
		if(user.getUsername().isEmpty() || user.getUsername().length() < 5 || user.getPassword().isEmpty() || user.getPassword().length() < 8)
		{
			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
		}
		
		if(!user.getPassword().equals(findById(user.getId()).getPassword())) encryptPassword(user);
		return userRepository.save(user);
	}
	
	/**
	 * Calls Password API to generate BCrypt passwords
	 * 
	 * @param user
	 */
	private void encryptPassword(User user) 
	{
		user.setPassword(PasswordService.hashPassword(user.getPassword()));
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id)
	{
		userRepository.deleteById(id);
	}
}
