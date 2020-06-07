package com.omm.jpa.repository;

import com.omm.jpa.model.entity.Bank;
import com.omm.jpa.model.entity.Currency;
import com.omm.jpa.model.entity.Party;
import com.omm.jpa.model.entity.PaymentTerm;
import com.omm.jpa.model.entity.PaymentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class PaymentTermRepositoryNotEmbebedDBTest {
    @Autowired
    private PaymentTermRepository paymentTermRepository;
    @Autowired
    private PartyRepository partyRepository;

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
    @Rollback(false)
    public void save() {
        paymentTerm.setPaymentKey("mariela");
        PaymentTerm d = paymentTermRepository.save(paymentTerm);
        assertNotNull(d.getPaymentTermId());
        System.out.println(d.getPaymentTermId());
        System.out.println(d.getPaymentKey());
        System.out.println(d.getParty().getFirstName());
        partyRepository.findAll().forEach(x -> System.out.println(x.getFirstName() + x.getPartyId()));

        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(d.getPaymentTermId());
        assertTrue(paymentTerm.isPresent());
        // assertEquals("Omar", paymentTerm.get().getParty().getFirstName());
    }


    @Test
    @Rollback(false)
    public void update() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);
        assertTrue(paymentTerm.isPresent());
        assertEquals("l/K51JlPpOAOU0P+sFC6Zo51if0Bti7j6Qc+/KicHEk=", paymentTerm.get().getInterAccountNbr());
        assertEquals("Barra", paymentTerm.get().getParty().getLastName());

        paymentTerm.get().setInterAccountNbr("12345");
        paymentTerm.get().setParty(new Party());
        paymentTerm.get().getParty().setPartyId(2L);
        paymentTerm.get().getParty().setFirstName("nametest");
        paymentTerm.get().getParty().setLastName("lastnametest");
        paymentTermRepository.save(paymentTerm.get());

        Optional<PaymentTerm> paymentTermUpdated = paymentTermRepository.findById(1L);
        Party party = paymentTermRepository.findPartyByPaymentTermId(1L);
        assertTrue(paymentTermUpdated.isPresent());
        assertEquals("12345", paymentTermUpdated.get().getInterAccountNbr());
        assertEquals("lastnametest", party.getLastName());
        assertEquals("BANCO DE GALICIA Y BUENOS AIRES", paymentTermUpdated.get().getBank().getDescription());
        assertEquals("PESOS ARGENTINOS", paymentTermUpdated.get().getCurrency().getDescription());
        assertEquals("DEBITO EN CUENTA", paymentTermUpdated.get().getPaymentType().getDescription());

    }

    @Test
    @Rollback(false)
    public void FINDD() {

        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(15l);
        assertTrue(paymentTerm.isPresent());

         assertEquals("Omar", paymentTerm.get().getParty().getFirstName());


    }
}