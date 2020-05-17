package com.omm.jpa.controller;

import com.omm.jpa.model.entity.PaymentTerm;
import com.omm.jpa.model.entity.PaymentTermPlain;
import com.omm.jpa.repository.BookRepository;
import com.omm.jpa.repository.CurrencyRepository;
import com.omm.jpa.repository.PaymentTermRepository;
import com.omm.jpa.repository.PaymentTermPlainRepository;
import com.omm.jpa.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@RestController
public class TestController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PaymentTermRepository paymentTermRepository;

    @Autowired
    private PaymentTermPlainRepository paymentTermPlainRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/all")
    public List<PaymentTerm> all(){
        //return paymentTermRepository.findAll();
        List<PaymentTerm> der = paymentTermRepository.findAll();
        return der;
    }

    @GetMapping("/allEntity")
    public Object alle(){
        return bookRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Object findById(@PathVariable Integer id) {
        return bookRepository.findById(id);
    }
    @GetMapping("/test")
    public Object test(){
        Query queryE = entityManager.createNativeQuery("select top 3 * from PAYMENT_TERM ");
        return queryE.getResultList();
    }

    @GetMapping("/findAllPlain")
    public Object findj(){
        return paymentTermPlainRepository.findAll();
    }

    @GetMapping("/findsim")
    public Object findsim(){
        Optional<PaymentTermPlain> queryE = paymentTermPlainRepository.findById(2L);
        return queryE;
    }

    @PostMapping("/save")
    public Object save(@RequestBody PaymentTerm paymentTerm){

        PaymentTerm queryE = paymentTermRepository.save(paymentTerm);

        return queryE;
    }
}
