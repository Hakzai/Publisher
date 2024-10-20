package com.akeir.publisher.controller;

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

import com.akeir.publisher.model.Author;
import com.akeir.publisher.repository.AuthorRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;
	
	@GetMapping("/list")
	public List<Author> list()
	{
		return authorRepository.findAll();
	}
	
	@GetMapping("/find/{id}")
	public Author findById(@PathVariable Integer id)
	{
		return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ENTITY NOT FOUND FOR ID " + id));
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Author create(@RequestBody Author author) throws OperationsException
	{
		if(author.getId() != null) throw new OperationsException("WRONG OPERATION. USE EDIT FOR SAVING EXISTING ENTITY");
		
		return authorRepository.save(author);
	}
	
	@PostMapping("/edit")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Author edit(@RequestBody Author author)
	{
		if(author.getName().isEmpty())
		{
			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
		}
		
		findById(author.getId());
		return authorRepository.save(author);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id)
	{
		authorRepository.deleteById(id);
	}
}