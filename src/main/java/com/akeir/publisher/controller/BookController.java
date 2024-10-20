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

import com.akeir.publisher.model.Book;
import com.akeir.publisher.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/list")
	public List<Book> list()
	{
		return bookRepository.findAll();
	}
	
	@GetMapping("/find/{id}")
	public Book findById(@PathVariable Integer id)
	{
		return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ENTITY NOT FOUND FOR ID " + id));
	}
	
	@GetMapping("/findBooksByAuthorId/{id}")
	public List<Book> findAllBooksByAuthorId(@PathVariable Integer id)
	{
		return bookRepository.findAll().stream()
				.filter(book -> book.retrieveAuthorId() == id)
				.toList();
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Book create(@RequestBody Book book) throws OperationsException
	{
		if(book.getId() != null) throw new OperationsException("WRONG OPERATION. USE EDIT FOR SAVING EXISTING ENTITY");
		
		return bookRepository.save(book);
	}
	
	@PostMapping("/edit")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Book edit(@RequestBody Book book)
	{
		if(book.getTitle().isEmpty() || book.getPublisher().isEmpty())
		{
			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
		}
		
		findById(book.getId());
		return bookRepository.save(book);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id)
	{
		bookRepository.deleteById(id);
	}
}
