package com.omm.jpa.repository;

import com.omm.jpa.model.entity.Party;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PartyRepositoryTest {

    @Autowired
    private PartyRepository partyRepository;

    @Test
    public void findAll() {
        List<Party> result = partyRepository.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void findByFirstName() {
        List<Party> result = partyRepository.findByFirstName("omar");
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void findByFirstNameIgnoreCase() {
        List<Party> result = partyRepository.findByFirstNameIgnoreCase("omar");
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void save() {
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

    @Test(expected = Exception.class)
    public void saveWhenSetNullInNullableFalseColumn() {
        Party party = new Party();
        party.setFirstName(null);
        party.setLastName("TestLastName");
        partyRepository.save(party);
    }
}