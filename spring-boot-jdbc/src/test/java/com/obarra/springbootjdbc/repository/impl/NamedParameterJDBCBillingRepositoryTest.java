package com.obarra.springbootjdbc.repository.impl;

import com.obarra.springbootjdbc.model.Billing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@DataJdbcTest
@ActiveProfiles("integrationtest")
class NamedParameterJDBCBillingRepositoryTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private NamedParameterJDBCBillingRepository namedParameterJDBCBillingRepository;

    private Billing firstBilling;

    @BeforeEach
    void setUp() {
        namedParameterJDBCBillingRepository = new NamedParameterJDBCBillingRepository(namedParameterJdbcTemplate);
        firstBilling = new Billing(1L,
                1L,
                null,
                LocalDate.of(2019,11, 1),
                BigDecimal.valueOf(30000.0));
    }

    /**
     * this test depends of positions
     * TODO change to avoid this dependency
     */
    @Test
    void count() {
        Assertions.assertEquals(2L, namedParameterJDBCBillingRepository.count());
    }

    /**
     * this test depends of positions
     * TODO change to avoid this dependency
     */
    @Test
    void filter() {
        Billing billing = new Billing();
        Map<Long, Map<String, Object>> result = namedParameterJDBCBillingRepository.filter(billing);
        Assertions.assertEquals(2L, result.size());
    }

    @Test
    void findById() {
        Optional<Billing> result = namedParameterJDBCBillingRepository.findById(1L);
        Optional<Billing> expected = Optional.of(firstBilling);

        Assertions.assertEquals(expected, result);
    }

    /**
     * this test depends of positions
     * TODO change to avoid this dependency
     */
    @Test
    void findAll() {
        List<Billing> result = namedParameterJDBCBillingRepository.findAll();

        Billing secondBilling = new Billing();
        secondBilling.setBillingId(2L);
        List<Billing> expected = Arrays.asList(firstBilling, secondBilling);

        Assertions.assertIterableEquals(expected, result);
    }

    @Test
    void saveAndReturnId() {
        firstBilling.setBillingId(null);
        Long billingId = namedParameterJDBCBillingRepository.saveAndReturnId(firstBilling);
        Optional<Billing> result = namedParameterJDBCBillingRepository.findById(billingId);

        firstBilling.setBillingId(billingId);
        Optional<Billing> expected = Optional.of(firstBilling);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void save() {
        firstBilling.setBillingId(null);
        Integer result = namedParameterJDBCBillingRepository.save(firstBilling);
        Assertions.assertEquals(1, result);
    }

    @Test
    void saveBatch() {
        Billing secondBilling = new Billing();
        secondBilling.setAmount(BigDecimal.TEN);
        firstBilling.setBillingId(null);
        List<Billing> billings = Arrays.asList(firstBilling, secondBilling);

        int[] result = namedParameterJDBCBillingRepository.save(billings);

        Assertions.assertArrayEquals(new int[]{1, 1}, result);
    }

    @Test
    void update() {
        firstBilling.setBillingId(null);
        firstBilling.setAmount(BigDecimal.ONE);

        Integer result = namedParameterJDBCBillingRepository.update(firstBilling, 1L);

        Assertions.assertEquals(1, result);
    }

    @Test
    void delete() {
        Integer result = namedParameterJDBCBillingRepository.delete(1L);

        Assertions.assertEquals(1, result);
    }
}