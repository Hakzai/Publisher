package com.akeir.publisher.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akeir.publisher.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	Optional<Author> findByName(String string);

}
