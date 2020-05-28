package com.omm.jpa.repository;

import com.omm.jpa.model.entity.PaymentTermPlain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PaymentTermPlainRepositoryTest {

    @Autowired
    private PaymentTermPlainRepository paymentTermPlainRepository;

    @Test
    public void findById() {
        Optional<PaymentTermPlain> paymentTermPlain = paymentTermPlainRepository.findById(1L);
        assertTrue(paymentTermPlain.isPresent());
        /**
        assertEquals(Long.valueOf(1L), paymentTermPlain.get().getBankId());
        assertEquals(Long.valueOf(1L),paymentTermPlain.get().getCurrencyId());
        assertEquals(Long.valueOf(1L),paymentTermPlain.get().getPaymentTypeId());
        assertEquals(Long.valueOf(1L),paymentTermPlain.get().getPartyId());*/
    }

}