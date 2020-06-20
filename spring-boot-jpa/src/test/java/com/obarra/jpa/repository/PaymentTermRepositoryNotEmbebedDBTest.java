package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.Party;
import com.obarra.jpa.model.entity.Bank;
import com.obarra.jpa.model.entity.Currency;
import com.obarra.jpa.model.entity.Party;
import com.obarra.jpa.model.entity.PaymentTerm;
import com.obarra.jpa.model.entity.PaymentType;
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
import static org.junit.Assert.assertFalse;
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
    public void updateDataOfCurrentParty() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);
        assertTrue(paymentTerm.isPresent());
        assertEquals("Barra", paymentTerm.get().getParty().getLastName());
        assertEquals("Omar", paymentTerm.get().getParty().getFirstName());

        paymentTerm.get().getParty().setFirstName("nametest");
        paymentTerm.get().getParty().setLastName("lastnametest");
        paymentTermRepository.save(paymentTerm.get());

        Optional<PaymentTerm> paymentTermUpdated = paymentTermRepository.findById(1L);
        Party party = paymentTermRepository.findPartyByPaymentTermId(1L);
        assertTrue(paymentTermUpdated.isPresent());
        assertEquals("lastnametest", party.getLastName());
        assertEquals("nametest", party.getFirstName());
    }

    @Test
    @Rollback(false)
    public void updateDataOfCurrentCurrency() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);
        assertTrue(paymentTerm.isPresent());
        assertEquals("PESOS ARGENTINOS", paymentTerm.get().getCurrency().getDescription());

        paymentTerm.get().getCurrency().setDescription("CURRENCY TEST");

        paymentTermRepository.save(paymentTerm.get());

        Optional<PaymentTerm> paymentTermUpdated = paymentTermRepository.findById(1L);
        assertEquals("CURRENCY TEST", paymentTermUpdated.get().getCurrency().getDescription());
    }

    @Test
    @Rollback(false)
    public void updateDataOfCurrentPaymentType() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);
        assertTrue(paymentTerm.isPresent());
        assertEquals("DEBITO EN CUENTA", paymentTerm.get().getPaymentType().getDescription());

        paymentTerm.get().getPaymentType().setDescription("PAYMENT TYPE TEST");

        paymentTermRepository.save(paymentTerm.get());

        Optional<PaymentTerm> paymentTermUpdated = paymentTermRepository.findById(1L);
        assertEquals("PAYMENT TYPE TEST", paymentTermUpdated.get().getPaymentType().getDescription());
    }

    @Test
    @Rollback(false)
    public void updateChangeParty() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);
        assertTrue(paymentTerm.isPresent());
        assertEquals(Long.valueOf(1L), paymentTerm.get().getParty().getPartyId());
        assertEquals("Barra", paymentTerm.get().getParty().getLastName());
        assertEquals("Omar", paymentTerm.get().getParty().getFirstName());

        paymentTerm.get().setParty(new Party());
        paymentTerm.get().getParty().setPartyId(2L);
        paymentTerm.get().getParty().setFirstName("nametest");
        paymentTerm.get().getParty().setLastName("lastnametest");
        paymentTermRepository.save(paymentTerm.get());

        Optional<PaymentTerm> paymentTermUpdated = paymentTermRepository.findById(1L);
        Party party = paymentTermRepository.findPartyByPaymentTermId(1L);
        assertTrue(paymentTermUpdated.isPresent());
        assertEquals(Long.valueOf(2L), party.getPartyId());
        assertEquals("lastnametest", party.getLastName());
        assertEquals("nametest", party.getFirstName());
    }

    @Test
    @Rollback(false)
    public void deleteById() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);
        assertTrue(paymentTerm.isPresent());

        paymentTermRepository.deleteById(paymentTerm.get().getPaymentTermId());

        Optional<PaymentTerm> paymentTermUpdated = paymentTermRepository.findById(1L);
        assertFalse(paymentTermUpdated.isPresent());
    }

    @Test
    @Rollback(false)
    public void FINDD() {

        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(15l);
        assertTrue(paymentTerm.isPresent());

         assertEquals("Omar", paymentTerm.get().getParty().getFirstName());


    }
}