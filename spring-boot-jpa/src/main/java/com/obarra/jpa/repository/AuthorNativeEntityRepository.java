package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.AuthorNativeEntity;
import com.obarra.jpa.model.entity.AuthorSimplified;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface AuthorNativeEntityRepository extends Repository<AuthorNativeEntity, Object> {

    @Query(nativeQuery = true)
    List<AuthorSimplified> executeQuery();
}
