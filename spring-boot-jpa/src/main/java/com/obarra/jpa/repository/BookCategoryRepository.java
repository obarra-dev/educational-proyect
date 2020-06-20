package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer>{
}