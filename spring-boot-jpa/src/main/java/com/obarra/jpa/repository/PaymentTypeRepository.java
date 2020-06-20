package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.PaymentType;
import org.springframework.data.repository.CrudRepository;


public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {
}
