package com.omm.jpa.repository;

import com.omm.jpa.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByFirstName(String firstName);

    List<Author> findByFirstNameIgnoreCase(String firstName);



}
