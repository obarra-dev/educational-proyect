package com.omm.jpa.repository;

import com.omm.jpa.model.entity.PaymentTermPlain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTermPlainRepository extends JpaRepository<PaymentTermPlain, Long> {

}
