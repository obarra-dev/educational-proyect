package com.omm.jpa.controller;

import com.omm.jpa.model.entity.PaymentTerm;
import com.omm.jpa.model.entity.PaymentTermSimple;
import com.omm.jpa.repository.PaymentTermRepository;
import com.omm.jpa.repository.PaymentTermSimpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@RestController
public class TestController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PaymentTermRepository paymentTermRepository;

    @Autowired
    private PaymentTermSimpleRepository paymentTermSimpleRepository;

    @GetMapping("/test")
    public Object test(){
        Query queryE = entityManager.createNativeQuery("select top 3 * from PAYMENT_TERM ");
        return queryE.getResultList();
    }

    @GetMapping("/find")
    public Object find(){
        Optional<PaymentTerm> queryE = paymentTermRepository.findById(2L);
        return queryE;
    }

    @GetMapping("/findsim")
    public Object findsim(){
        Optional<PaymentTermSimple> queryE = paymentTermSimpleRepository.findById(2L);
        return queryE;
    }

    @PostMapping("/save")
    public Object save(@RequestBody PaymentTerm paymentTerm){

        PaymentTerm queryE = paymentTermRepository.save(paymentTerm);

        return queryE;
    }
}
