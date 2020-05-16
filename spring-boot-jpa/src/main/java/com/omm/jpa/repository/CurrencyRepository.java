package com.omm.jpa.repository;

import com.omm.jpa.model.entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
