package com.obarra.springbootjdbc.repository;

import com.obarra.springbootjdbc.model.Billing;

import java.beans.BeanInfo;
import java.util.List;
import java.util.Optional;


public interface BillingRepository {
    Long count();

    Optional<Billing> findById(Long billingId);

    List<Billing> findAll();

    Long saveAndReturnId(Billing billing);

    Integer save(Billing billing);

    Integer update(Billing billing, Long billingId);

    Integer delete(Long billingId);
}
