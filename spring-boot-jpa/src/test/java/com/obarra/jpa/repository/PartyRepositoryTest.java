package com.obarra.jpa.repository;

import com.obarra.jpa.projection.PartyProjected;
import com.obarra.jpa.model.entity.Bank;
import com.obarra.jpa.model.entity.Party;
import com.obarra.jpa.model.entity.PaymentTerm;
import com.obarra.jpa.model.entity.PaymentType;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PartyRepositoryTest {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    private PaymentTerm paymentTerm;

    @Before
    public void setUp() {
        paymentTerm = new PaymentTerm();
        PaymentType paymentType = new PaymentType();
        paymentType.setPaymentTypeId(2000L);
        paymentTerm.setPaymentType(paymentType);
        paymentTerm.setCurrency(currencyRepository.findById(1000L));
        Bank bank = new Bank();
        bank.setBankId(1L);
        paymentTerm.setBank(bank);
    }


    @Test
    public void findAll() {
        List<Party> result = partyRepository.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
    }

    /**
     * Aparentemente no hace falta setear a cada hijo quien es el padre?? validar
     */
    @Test
    public void findById() {
        Optional<Party> party = partyRepository.findById(1L);
        Assert.assertNotNull(party);
        Assert.assertEquals(3,
                party.get().getPaymentTerms().size());

        party.get().getPaymentTerms()
                .forEach(x -> {
                    Assert.assertNotNull(x.getPaymentTermId());
                    Assert.assertEquals(party.get().getPartyId(),  x.getParty().getPartyId());
                    Assert.assertSame(party.get(), x.getParty());
                });
    }


    @Test
    public void findByFirstNameIgnoreCase() {
        List<Party> result = partyRepository.findByFirstNameIgnoreCase("omar");
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void findByFirstName(){
        List<Party> result = partyRepository.findByFirstName("Omar");
        MatcherAssert.assertThat(result, Matchers.<Party>hasSize(1));
    }

    @Test
    public void findByFirstNameEmpty() {
        List<Party> result = partyRepository.findByFirstName("omar");
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void findByPaymentTerms(){
        List<PaymentTerm> paymentTerms = new ArrayList<>();
        PaymentTerm paymentTerm = new PaymentTerm();
        paymentTerm.setPaymentTermId(1L);
        paymentTerms.add(paymentTerm);
        List<Party> result = partyRepository.findByPaymentTerms(paymentTerms);
        MatcherAssert.assertThat(result, Matchers.<Party>hasSize(1));
    }

    @Test
    public void findByPaymentTermsIn(){
        List<PaymentTerm> paymentTerms = new ArrayList<>();
        PaymentTerm paymentTerm = new PaymentTerm();
        paymentTerm.setPaymentTermId(1L);
        paymentTerms.add(paymentTerm);

        paymentTerm = new PaymentTerm();
        paymentTerm.setPaymentTermId(5L);
        paymentTerms.add(paymentTerm);

        List<Party> result = partyRepository.findByPaymentTermsIn(paymentTerms);
        MatcherAssert.assertThat(result, Matchers.<Party>hasSize(2));
    }

    @Test
    public void findByPaymentTermIds(){
        List<Party> result = partyRepository.findByPaymentTermIds(Arrays.asList(1L, 5L));
        MatcherAssert.assertThat(result, Matchers.<Party>hasSize(2));
    }

    @Test
    public void findByPaymentTermIdsWithArrays(){
        List<Party> result = partyRepository.findByPaymentTermIds(new Long[] {1L, 5L});
        MatcherAssert.assertThat(result, Matchers.<Party>hasSize(2));
    }

    @Test
    public void findByFirstNameNot(){
        Party authorExpected = new Party("Omar", "Barra");
        List<Party> result = partyRepository.findByFirstName("omar");
        MatcherAssert.assertThat(result, IsNot.not(Matchers.contains(authorExpected)));
    }

    @Test
    public void findAllProjected(){
        List<PartyProjected> result = partyRepository.findAllProjected("Omar");
        for (PartyProjected partyProjected:result) {
            Assert.assertEquals("Omar", partyProjected.getName());
            Assert.assertEquals("Barra", partyProjected.getSurname());
            Assert.assertEquals("1", partyProjected.getId());
        }
    }

    @Test(expected = Exception.class)
    public void saveWhenSetNullInNullableFalseColumn() {
        Party party = new Party();
        party.setFirstName(null);
        party.setLastName("TestLastName");
        partyRepository.save(party);
    }

    @Test
    public void savePartyWithOutChildren() {
        Party party = new Party();
        party.setFirstName("TestName");
        party.setLastName("TestLastName");
        partyRepository.save(party);

        Assert.assertNotNull(party.getPartyId());

        List<Party> parties = partyRepository.findAll();
        Assert.assertEquals(3, parties.size());

        List<Party> createdParty = partyRepository.findByFirstNameIgnoreCase("TestName");
        Assert.assertEquals("TestName", createdParty.get(0).getFirstName());
        Assert.assertEquals("TestLastName", createdParty.get(0).getLastName());
    }

    @Test
    public void savePartyWithChildren() {
        Party party = new Party();
        party.setFirstName("TestName");
        party.setLastName("TestLastName");
        paymentTerm.setParty(party);
        Set<PaymentTerm> paymentTermSet = new HashSet<>();
        paymentTermSet.add(paymentTerm);
        party.setPaymentTerms(paymentTermSet);

        partyRepository.save(party);

        Assert.assertNotNull(party.getPartyId());

        List<Party> parties = partyRepository.findAll();
        Assert.assertEquals(3, parties.size());

        List<Party> createdParty = partyRepository.findByFirstNameIgnoreCase("TestName");
        Assert.assertEquals("TestName", createdParty.get(0).getFirstName());
        Assert.assertEquals("TestLastName", createdParty.get(0).getLastName());
        Assert.assertEquals(1,
                createdParty.get(0).getPaymentTerms().size());
        createdParty.get(0).getPaymentTerms()
                .forEach(x -> {
                    Assert.assertNotNull(x.getPaymentTermId());
                    Assert.assertEquals(createdParty.get(0).getPartyId(),  x.getParty().getPartyId());
                    Assert.assertSame(createdParty.get(0), x.getParty());
                });
    }
}