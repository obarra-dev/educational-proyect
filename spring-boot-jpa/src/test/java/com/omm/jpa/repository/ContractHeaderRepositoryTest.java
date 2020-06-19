package com.omm.jpa.repository;

import com.omm.jpa.model.entity.ContractHeader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Test
    public void findByCoveragePlanIdPageableSortDefaultIdAndSort() {
        Page<ContractHeader> page = contractHeaderRepository
                .findByCoveragePlanId(3L, new PageRequest(0, 2));
        assertEquals(5, page.getTotalElements());
        assertEquals(2, page.getSize());
        assertEquals(2, page.getContent().size());
        assertTrue(page.getContent().get(0).getContractId()
                < page.getContent().get(1).getContractId());


        page = contractHeaderRepository
                .findByCoveragePlanId(3L, new PageRequest(1, 2));
        assertEquals(5, page.getTotalElements());
        assertEquals(2, page.getSize());
        assertEquals(2, page.getContent().size());
        assertTrue(page.getContent().get(0).getContractId()
                < page.getContent().get(1).getContractId());

        page = contractHeaderRepository
                .findByCoveragePlanId(3L, new PageRequest(2, 2));
        assertEquals(5, page.getTotalElements());
        assertEquals(2, page.getSize());
        assertEquals(1, page.getContent().size());
    }

    @Test
    public void findByCoveragePlanIdPageableSortDESCAndExplicitId() {
        Page<ContractHeader> page = contractHeaderRepository
                .findByCoveragePlanId(3L,
                        new PageRequest(0, 2,
                        Sort.Direction.DESC,
                        "contractId"));
        assertEquals(5, page.getTotalElements());
        assertEquals(2, page.getSize());
        assertEquals(2, page.getContent().size());

        Page<ContractHeader> lastPage = contractHeaderRepository
                .findByCoveragePlanId(3L,
                        new PageRequest(2, 2));

        // compare the first element in a DESC list with the last element in a ASC list
        assertEquals(lastPage.getContent().get(0).getContractId()
                , page.getContent().get(0).getContractId());
    }

    @Test
    public void findByCoveragePlanIdJPQLWithFilter() {
        Page<ContractHeader> page = contractHeaderRepository
                .findByCoveragePlanIdJPQL(null, new PageRequest(0, 2));
        assertEquals(5, page.getTotalElements());
        assertEquals(2, page.getSize());
        assertEquals(2, page.getContent().size());
        assertTrue(page.getContent().get(0).getContractId()
                < page.getContent().get(1).getContractId());
    }

    @Test
    public void findByCoveragePlanIdJPQLSortByContractFrom() {
        Page<ContractHeader> pageDESC = contractHeaderRepository
                .findByCoveragePlanIdJPQL(null, new PageRequest(0, 5,
                        Sort.Direction.DESC,
                        "contractFrom"));
        assertEquals(5, pageDESC.getTotalElements());
        assertEquals(5, pageDESC.getSize());
        assertEquals(5, pageDESC.getContent().size());


        Page<ContractHeader> pageASC = contractHeaderRepository
                .findByCoveragePlanIdJPQL(null, new PageRequest(0, 5,
                        Sort.Direction.ASC,
                        "contractFrom"));
        assertEquals(5, pageASC.getTotalElements());
        assertEquals(5, pageASC.getSize());
        assertEquals(5, pageASC.getContent().size());

        assertEquals(pageDESC.getContent().get(0).getContractId(), pageASC.getContent().get(4).getContractId());
        assertEquals(pageDESC.getContent().get(4).getContractId(), pageASC.getContent().get(0).getContractId());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findByCoveragePlanIdJPQLSortWithIncorrectProperty() {
        contractHeaderRepository
                .findByCoveragePlanIdJPQL(null, new PageRequest(0, 5,
                        Sort.Direction.DESC,
                        "contractFromXX"));
    }

    @Test
    public void findByCoveragePlanIdSQL() {
        List<Object[]> list = contractHeaderRepository
                .findByCoveragePlanIdSQL(null);
        assertEquals(5, list.size());
    }

    @Test
    public void findByCoveragePlanIdPageableSQL() {
        Page<Object[]> page = contractHeaderRepository
                .findByCoveragePlanIdPageableSQL(null, new PageRequest(0, 5));
        assertEquals(5, page.getContent().size());
    }

}