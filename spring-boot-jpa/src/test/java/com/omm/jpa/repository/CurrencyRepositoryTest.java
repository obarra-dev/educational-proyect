package com.omm.jpa.repository;

import com.omm.jpa.model.entity.Currency;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void findAll() {
        List<Currency> result = currencyRepository.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void findById() {
        Currency currency = currencyRepository.findById(2000L);
        Assert.assertNotNull(currency);
        Assert.assertEquals("DOLARES ESTADOUNIDENSES", currency.getDescription());
    }

}