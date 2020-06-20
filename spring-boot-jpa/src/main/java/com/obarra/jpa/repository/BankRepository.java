package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.Bank;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BankRepository extends Repository<Bank, Long> {
    List<Bank> findAll();

    Bank findById(Long id);
}
