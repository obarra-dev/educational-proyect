package com.omm.jpa.repository;

import com.omm.jpa.model.entity.Author;
import com.omm.jpa.model.entity.AuthorProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface AuthorNativeRepository extends Repository<Author, Object> {

    @Query(value = "select  first_name as name, last_name as surname from AUTHOR", nativeQuery = true)
    List<AuthorProjection> findFirstByFirstName();
}
