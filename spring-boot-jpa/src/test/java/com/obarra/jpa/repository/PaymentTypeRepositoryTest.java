package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.PaymentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PaymentTypeRepositoryTest {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findAll() {
        Iterable<PaymentType> types = paymentTypeRepository.findAll();
        List<PaymentType> result =  new ArrayList<>();
        types.forEach(result::add);
        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void findById() {
        Optional<PaymentType> paymentType = paymentTypeRepository.findById(2000L);
        Assert.assertTrue(paymentType.isPresent());
        Assert.assertEquals(Long.valueOf(2000), paymentType.get().getPaymentTypeId());
    }

    @Test
    public void save() {
        //TODO search how disable save operations in CRUD REPOSITOY
        PaymentType paymentType = new PaymentType();
        paymentType.setActive(true);
        paymentType.setDescription("ThisIsTest");
        paymentType.setNomenclature("test");
        PaymentType result = paymentTypeRepository.save(paymentType);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getPaymentTypeId());
    }
}