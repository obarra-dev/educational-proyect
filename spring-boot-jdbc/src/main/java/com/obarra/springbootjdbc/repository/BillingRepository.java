package com.obarra.springbootjdbc.repository;

import com.obarra.springbootjdbc.model.Billing;

import java.util.List;
import java.util.Optional;


public interface BillingRepository {
    Integer count();

    Optional<Billing> findById(Long billingId);

    List<Billing> findAll();

    Integer save(Billing billing);

    Integer update(Billing billing, Integer billingId);

    Integer delete(Integer billingId);
}
