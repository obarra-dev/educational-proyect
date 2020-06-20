package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.Currency;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CurrencyRepository extends Repository<Currency, Long> {
    List<Currency> findAll();

    Currency findById(Long id);
}
