package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.Bank;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BankRepositoryTest {

    @Autowired
    private BankRepository bankRepository;

    @Test
    public void findAll() {
        List<Bank> result = bankRepository.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.size());
    }

    @Test
    public void findById() {
        Bank bank = bankRepository.findById(1L);
        Assert.assertNotNull(bank);
        Assert.assertEquals("BANCO DE GALICIA Y BUENOS AIRES", bank.getDescription());
    }
}