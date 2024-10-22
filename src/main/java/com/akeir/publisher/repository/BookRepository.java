package com.akeir.publisher.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akeir.publisher.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<List<Book>> findByTitle(String title);

}
