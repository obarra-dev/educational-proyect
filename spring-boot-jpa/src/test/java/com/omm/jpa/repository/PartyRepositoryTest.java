package com.omm.jpa.repository;

import com.omm.jpa.model.entity.Party;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}