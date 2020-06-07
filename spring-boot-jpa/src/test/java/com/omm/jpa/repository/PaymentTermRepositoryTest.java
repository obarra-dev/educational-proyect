package com.omm.jpa.repository;

import com.omm.jpa.dto.PaymentTermDTO;
import com.omm.jpa.model.entity.Bank;
import com.omm.jpa.model.entity.Currency;
import com.omm.jpa.model.entity.Party;
import com.omm.jpa.model.entity.PaymentTerm;
import com.omm.jpa.model.entity.PaymentType;
import org.hibernate.proxy.HibernateProxy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PaymentTermRepositoryTest {
    @Autowired
    private PaymentTermRepository paymentTermRepository;
    private PaymentTerm paymentTerm;

    @Before
    public void setUp() {
        paymentTerm = new PaymentTerm();
        Party party = new Party();
        party.setPartyId(1L);
        paymentTerm.setParty(party);
        PaymentType paymentType = new PaymentType();
        paymentType.setPaymentTypeId(2000L);
        paymentTerm.setPaymentType(paymentType);
        Currency currency = new Currency();
        currency.setCurrencyId(1000L);
        paymentTerm.setCurrency(currency);
        Bank bank = new Bank();
        bank.setBankId(1L);
        paymentTerm.setBank(bank);
    }

    @Test
    public void findById() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);
        assertTrue(paymentTerm.isPresent());
        assertEquals("BANCO DE GALICIA Y BUENOS AIRES", paymentTerm.get().getBank().getDescription());
        assertEquals("PESOS ARGENTINOS", paymentTerm.get().getCurrency().getDescription());
        assertEquals("DEBITO EN CUENTA", paymentTerm.get().getPaymentType().getDescription());
        assertEquals("Barra", paymentTerm.get().getParty().getLastName());
    }

    @Test
    public void findByIdWhenInvokeEagerJoinColumn() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);

        assertTrue(paymentTerm.isPresent());
        assertEquals("BANCO DE GALICIA Y BUENOS AIRES", paymentTerm.get().getBank().getDescription());

        assertEquals(Bank.class, paymentTerm.get().getBank().getClass());
    }

    @Test
    public void findByIdWhenInvokeLazyJoinColumn() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);

        assertTrue(paymentTerm.isPresent());

        assertEquals("Barra", paymentTerm.get().getParty().getLastName());

        assertNotEquals( Party.class, paymentTerm.get().getParty().getClass());
        assertEquals( "org.hibernate.proxy.HibernateProxy",
                paymentTerm.get().getParty().getClass().getInterfaces()[0].getName());
        assertEquals( "org.hibernate.proxy.ProxyConfiguration",
                paymentTerm.get().getParty().getClass().getInterfaces()[1].getName());
    }

    @Test
    public void findByPartyFirstName() {
        List<PaymentTerm> paymentTerms = paymentTermRepository.findByPartyFirstName("Omar");
        Assert.assertEquals(3, paymentTerms.size());
        Assert.assertEquals("Omar", paymentTerms.get(0).getParty().getFirstName());
        Assert.assertEquals("Barra", paymentTerms.get(0).getParty().getLastName());
    }

    @Test
    public void findPaymentTermDTOById() {
        PaymentTermDTO dto = paymentTermRepository.findPaymentTermDTOById(1L);
        Assert.assertEquals("BANCO DE GALICIA Y BUENOS AIRES", dto.getBankDescription());
        Assert.assertEquals("PESOS ARGENTINOS", dto.getCurrencyDescription());
        Assert.assertEquals("DEBITO EN CUENTA", dto.getPaymentTermTypeDescription());
    }


    @Test
    public void findPartyByPaymentTermId() {
        Party party = paymentTermRepository.findPartyByPaymentTermId(1L);
        Assert.assertEquals("Omar", party.getFirstName());
        Assert.assertEquals("Barra", party.getLastName());
    }

    @Test
    public void save() {
        paymentTerm.setPaymentKey("mar");
        paymentTerm.getParty().setPartyId(2L);
        assertEquals(5, paymentTermRepository.count());

        PaymentTerm createdPaymentTerm = paymentTermRepository.save(paymentTerm);

        assertNotNull(createdPaymentTerm.getPaymentTermId());

        assertEquals(6, paymentTermRepository.count());

        Party party = paymentTermRepository.findPartyByPaymentTermId(createdPaymentTerm.getPaymentTermId());
        Assert.assertEquals("Maru", party.getFirstName());
        Assert.assertEquals("Elen", party.getLastName());
    }
}