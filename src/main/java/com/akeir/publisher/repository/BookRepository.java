package com.akeir.publisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akeir.publisher.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
