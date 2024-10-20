package com.akeir.publisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akeir.publisher.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
