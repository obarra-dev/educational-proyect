package com.omm.jpa.repository;

import com.omm.jpa.model.entity.PaymentTerm;
import com.omm.jpa.model.entity.PaymentTermSimple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTermSimpleRepository extends JpaRepository<PaymentTermSimple, Long> {

}
