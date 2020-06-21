package com.obarra.jpa.repository;

import com.obarra.jpa.dto.PaymentTermDTO;
import com.obarra.jpa.model.entity.Bank;
import com.obarra.jpa.model.entity.Currency;
import com.obarra.jpa.model.entity.Party;
import com.obarra.jpa.model.entity.PaymentTerm;
import com.obarra.jpa.model.entity.PaymentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PaymentTermRepositoryTest {
    @Autowired
    private PaymentTermRepository paymentTermRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
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

        Set<PaymentTerm> paymentTerms = paymentTerm.get().getParty().getPaymentTerms();
        assertEquals(3L, paymentTerms.size());

        paymentTerms
                .forEach(x -> {
                    Assert.assertNotNull(x.getPaymentTermId());
                });

        Optional<PaymentTerm> samePaymentTerm = paymentTerms.stream().filter(x -> x == paymentTerm.get()).findFirst();
        Assert.assertNotNull(samePaymentTerm.get());
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
    public void findByParty_FirstName_AndParty_LastName() {
        List<PaymentTerm> paymentTerm = paymentTermRepository.findByParty_FirstName_AndParty_LastName("Omar", "Barra");
        Assert.assertEquals(3, paymentTerm.size());
    }

    /**
     * ExampleMatcher generates dynamic queries so it can be used to filter
     */
    @Test
    public void findAllWithExampleMatcher() {
        //matchingAll = matching = and
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll();
        PaymentTerm paymentTerm = new PaymentTerm();
        paymentTerm.setCreditCardId(1L);
        paymentTerm.setPeriodId(5L);
        Bank bank = new Bank();
        bank.setBankId(1L);
        paymentTerm.setBank(bank);
        List<PaymentTerm> list = paymentTermRepository.findAll(Example.of(paymentTerm, exampleMatcher));
        Assert.assertEquals(1L, list.size());
    }

    @Test
    public void existsById() {
        Assert.assertFalse(paymentTermRepository.existsById(99L));
    }

    @Test
    public void existWhitOutCreditCardIdUsingJPQLQuery() {
        Assert.assertTrue(paymentTermRepository.existWhitOutCreditCardId());
    }

    @Test
    public void existsByCreditCardIdIsNullUsingDerivedQueryMethod() {
        Assert.assertTrue(paymentTermRepository.existsByCreditCardIdIsNull());
    }

    @Test
    public void existWhitOutCreditCardIdWithExampleMatcher() {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withIncludeNullValues()
                .withIgnorePaths("paymentTermId", "party",
                        "paymentType","currency",
                        "bank",
                        "accountNbr", "interAccountNbr",
                        "expiration", "paymentKey",
                        "bankBranchId", "periodId");
        PaymentTerm paymentTerm = new PaymentTerm();
        paymentTerm.setCreditCardId(1L);

        Assert.assertTrue(paymentTermRepository.exists(Example.of(paymentTerm, exampleMatcher)));

        List<PaymentTerm> list = paymentTermRepository.findAll(Example.of(paymentTerm, exampleMatcher));
        Assert.assertEquals(2L, list.size());
    }

    @Test
    public void existsWithExampleMatcher() {
        //matchingAll = matching = and
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase("accountNbr");
        PaymentTerm paymentTerm = new PaymentTerm();
        paymentTerm.setAccountNbr("qMnGF8608nv");
        Assert.assertTrue(paymentTermRepository.exists(Example.of(paymentTerm, exampleMatcher)));
    }

    @Test
    public void saveWithNewCurrencyCascadeALL() {
        assertEquals(5, paymentTermRepository.count());
        Currency currency = new Currency();
        currency.setCurrencyId(9L);
        currency.setDescription("currencytest");
        paymentTerm.setCurrency(currency);

        PaymentTerm createdPaymentTerm = paymentTermRepository.save(paymentTerm);

        assertNotNull(createdPaymentTerm.getPaymentTermId());

        assertEquals(6, paymentTermRepository.count());

        PaymentTerm newPaymentTerm = paymentTermRepository.findAll().stream()
                .filter(p -> p.getPaymentTermId().equals(createdPaymentTerm.getPaymentTermId()))
                .findFirst().get();
        Assert.assertEquals("currencytest", newPaymentTerm.getCurrency().getDescription());
    }

    @Test
    public void saveWithExistFoundedCurrencyCascadeALL() {
        paymentTerm.setCurrency(currencyRepository.findById(1000L));
        assertEquals(5, paymentTermRepository.count());

        PaymentTerm createdPaymentTerm = paymentTermRepository.save(paymentTerm);

        assertNotNull(createdPaymentTerm.getPaymentTermId());
        assertEquals(6, paymentTermRepository.count());

        PaymentTerm newPaymentTerm = paymentTermRepository.findAll().stream()
                .filter(p -> p.getPaymentTermId().equals(createdPaymentTerm.getPaymentTermId()))
                .findFirst().get();
        Assert.assertEquals("PESOS ARGENTINOS", newPaymentTerm.getCurrency().getDescription());
    }

    /**
     * If the attribute is Cascade ALL, the value exist in DB and only we use the id,
     * hibernate try to insert this attribute as a new element
     */
    @Test(expected = DataIntegrityViolationException.class)
    public void saveWithExistCurrencyCascadeALL() {
        paymentTerm.getCurrency().setCurrencyId(1000L);
        assertEquals(5, paymentTermRepository.count());

        PaymentTerm createdPaymentTerm = paymentTermRepository.save(paymentTerm);

        assertNotNull(createdPaymentTerm.getPaymentTermId());
        assertEquals(6, paymentTermRepository.count());

        PaymentTerm newPaymentTerm = paymentTermRepository.findAll().stream()
                .filter(p -> p.getPaymentTermId().equals(createdPaymentTerm.getPaymentTermId()))
                .findFirst().get();
        Assert.assertEquals("PESOS ARGENTINOS", newPaymentTerm.getCurrency().getDescription());
    }

    /**
     * in this test we insert payment term and a party with cascade MERGE and only we set partyId.
     * The payment term was inserted and linked with Party.
     * The values of the current Party was not updated.
     */
    @Test
    @Rollback(false)
    public void saveWithExistPartyCascadeMERGE() {
        paymentTerm.setCurrency(currencyRepository.findById(1000L));
        paymentTerm.getParty().setPartyId(2L);
        assertEquals(5, paymentTermRepository.count());

        PaymentTerm createdPaymentTerm = paymentTermRepository.save(paymentTerm);

        assertNotNull(createdPaymentTerm.getPaymentTermId());

        assertEquals(6, paymentTermRepository.count());

        Party party = paymentTermRepository.findPartyByPaymentTermId(createdPaymentTerm.getPaymentTermId());
        Assert.assertEquals("Maru", party.getFirstName());
        Assert.assertEquals("Elen", party.getLastName());
    }

    /**
     * in this test we insert payment term and a party with cascade MERGE and only we set partyId.
     * The payment term was inserted and linked with Party.
     * The values of the current Party was not updated.
     */
    @Test
    public void saveWithExistPartyAndPartysValuesOthersCascadeMERGE() {
        assertEquals(5, paymentTermRepository.count());

        paymentTerm.setCurrency(currencyRepository.findById(1000L));
        Party party = new Party();
        party.setFirstName("firstName");
        party.setLastName("lastName");
        party.setPartyId(2L);
        paymentTerm.setParty(party);

        PaymentTerm createdPaymentTerm = paymentTermRepository.save(paymentTerm);

        assertNotNull(createdPaymentTerm.getPaymentTermId());

        assertEquals(6, paymentTermRepository.count());

        Party createdParty = paymentTermRepository.findPartyByPaymentTermId(createdPaymentTerm.getPaymentTermId());
        Assert.assertEquals("Maru", createdParty.getFirstName());
        Assert.assertEquals("Elen", createdParty.getLastName());
    }

    /**
     * Try to link the new payment term with does not existed Party.
     * So, do not insert a new Party with Cascade MERGE
     */
    @Test(expected = DataIntegrityViolationException.class)
    public void saveWithNewPartyCascadeMERGE() {
        assertEquals(5, paymentTermRepository.count());

        paymentTerm.getParty().setPartyId(40L);

        PaymentTerm createdPaymentTerm = paymentTermRepository.save(paymentTerm);

        assertNotNull(createdPaymentTerm.getPaymentTermId());

        assertEquals(6, paymentTermRepository.count());

        Party createdParty = paymentTermRepository.findPartyByPaymentTermId(createdPaymentTerm.getPaymentTermId());
        Assert.assertEquals("Maru", createdParty.getFirstName());
        Assert.assertEquals("Elen", createdParty.getLastName());
    }
    @Test
    public void saveWithNewPartyCascadeALL() {
        assertEquals(5, paymentTermRepository.count());

        paymentTerm.setCurrency(currencyRepository.findById(1000L));
        Party party = new Party();
        party.setFirstName("firstName");
        party.setLastName("lastName");
        party.setPartyId(788L);
        paymentTerm.setParty(party);

        /**
       TODO si en party se cambia a cascade ALL se deberia poder guardar pero tira este
        Caused by: org.hibernate.PersistentObjectException: detached entity passed to persist: com.obarra.jpa.model.entity.Party
                Este mismo caso se puede hacer si ningun problema con currency. ¡Xq no funciona para Party?
         */
    }


    /**
     * Update Operation in main object updates main data and the data of the manyToOne attributes.
     * Does not matter the type of cascade.
     * So If you want to update a specific attribute of a object, you need to get all the rest of data
     * to avoid inconsistencies.
     * Note: the update operations is going to execute in a any moment
     * after saving method but not immediately after it.
     */
    @Test
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
    public void updateChangeCurrency() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);
        assertTrue(paymentTerm.isPresent());
        assertEquals(Long.valueOf(1000L), paymentTerm.get().getCurrency().getCurrencyId());
        assertEquals("PESOS ARGENTINOS", paymentTerm.get().getCurrency().getDescription());

        paymentTerm.get().setCurrency(new Currency());
        paymentTerm.get().getCurrency().setCurrencyId(2000L);

        paymentTermRepository.save(paymentTerm.get());

        Optional<PaymentTerm> paymentTermUpdated = paymentTermRepository.findById(1L);
        assertEquals(Long.valueOf(2000L), paymentTermUpdated.get().getCurrency().getCurrencyId());
        assertNull(paymentTermUpdated.get().getCurrency().getDescription());
    }

    @Test
    public void updateDataOfCurrentPaymentType() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(1L);
        assertTrue(paymentTerm.isPresent());
        assertEquals("DEBITO EN CUENTA", paymentTerm.get().getPaymentType().getDescription());

        paymentTerm.get().getPaymentType().setDescription("PAYMENT TYPE TEST");

        paymentTermRepository.save(paymentTerm.get());

        Optional<PaymentTerm> paymentTermUpdated = paymentTermRepository.findById(1L);
        assertEquals("PAYMENT TYPE TEST", paymentTermUpdated.get().getPaymentType().getDescription());
    }

    /**
     * This delete payment term, currency (Cascade ALL)
     */
    @Test
    @Rollback(false)
    public void deleteById() {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(5L);
        assertTrue(paymentTerm.isPresent());

        paymentTermRepository.deleteById(paymentTerm.get().getPaymentTermId());

        Optional<PaymentTerm> paymentTermUpdated = paymentTermRepository.findById(5L);
        assertFalse(paymentTermUpdated.isPresent());
    }
}