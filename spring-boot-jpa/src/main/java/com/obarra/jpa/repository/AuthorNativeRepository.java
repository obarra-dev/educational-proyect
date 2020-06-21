package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.Party;
import com.obarra.jpa.model.entity.AuthorProjected;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface AuthorNativeRepository extends Repository<Party, Object> {

    @Query(value = "select  first_name as name, last_name as surname from AUTHOR", nativeQuery = true)
    List<AuthorProjected> findFirstByFirstName();

    @Query(value = "select  first_name as firstName, last_name as lastName from AUTHOR", nativeQuery = true)
    List<Object> findAll();

    @Query(value = "select  count(1) from AUTHOR",
            nativeQuery = true)
    Long count();

    @Modifying
    @Query(value = "delete AUTHOR where FIRST_NAME = 'Omar'",
    nativeQuery = true)
    Integer deleteAllOmar();


}