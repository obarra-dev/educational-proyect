package com.omm.jpa.repository;

import com.omm.jpa.model.entity.ContractHeader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ContractHeaderRepositoryTest {

    @Autowired
    private ContractHeaderRepository contractHeaderRepository;

    @Test
    public void findDistinctContractFrom() {
        List<ContractHeader> contractHeaders = contractHeaderRepository
                .findDistinctByAgencyIdNotIn(Arrays.asList(2L, 3L));
        assertEquals(2, contractHeaders.size());
    }

    @Test(expected = ClassCastException.class)
    public void findInsurerIdByAgencyIdNotIn() {
        List<Long> ids = contractHeaderRepository
                .findInsurerIdByAgencyIdNotIn(Arrays.asList(2L, 3L));
        assertEquals(2, ids.size());
        for (Long id:ids) {
            System.out.println(id);
        }
    }

    /**
     * Distinct key word does not have magnitude because the distinct is applied with id of the table.
     * ¿Estudiar mas?
     */
    @Test
    public void findInsurerIdDistinctByAgencyIdIsNotNull() {
        List<ContractHeader> contractHeaders = contractHeaderRepository
                .findInsurerIdDistinctByAgencyIdIsNotNull();
        assertEquals(5, contractHeaders.size());
    }

    @Test
    public void findByAgencyIdIsNotNull() {
        List<ContractHeader> contractHeaders = contractHeaderRepository
                .findByAgencyIdIsNotNull();
        assertEquals(5, contractHeaders.size());
    }
}