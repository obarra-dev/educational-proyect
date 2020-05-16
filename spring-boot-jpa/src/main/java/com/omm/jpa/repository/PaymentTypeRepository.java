package com.omm.jpa.repository;

import com.omm.jpa.model.entity.PaymentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {
}
