package com.obarra.springbootjdbc.repository.impl;

import com.obarra.springbootjdbc.model.Billing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@DataJdbcTest
@ActiveProfiles("integrationtest")
class JDBCBillingRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JDBCBillingRepository jdbcBillingRepository;

    private Billing firstBilling;

    @BeforeEach
    void setUp() {
        jdbcBillingRepository = new JDBCBillingRepository(jdbcTemplate);
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
        Assertions.assertEquals(2L, jdbcBillingRepository.count());
    }

    /**
     * this test depends of positions
     * TODO change to avoid this dependency
     */
    @Test
    void filter() {
        Billing billing = new Billing();
        Map<Long, Map<String, Object>> result = jdbcBillingRepository.filter(billing);
        Assertions.assertEquals(2L, result.size());
    }

    @Test
    void findById() {
        Optional<Billing> result = jdbcBillingRepository.findById(1L);
        Optional<Billing> expected = Optional.of(firstBilling);

        Assertions.assertEquals(expected, result);
    }

    /**
     * this test depends of positions
     * TODO change to avoid this dependency
     */
    @Test
    void findAll() {
        List<Billing> result = jdbcBillingRepository.findAll();

        Billing secondBilling = new Billing();
        secondBilling.setBillingId(2L);
        List<Billing> expected = Arrays.asList(firstBilling, secondBilling);

        Assertions.assertIterableEquals(expected, result);
    }

    /**
     * this test depends of positions
     * TODO change to avoid this dependency
     */
    @Test
    void saveAndReturnId() {
        firstBilling.setBillingId(null);
        Long result = jdbcBillingRepository.saveAndReturnId(firstBilling);
        Assertions.assertEquals(3L, result);
    }

    @Test
    void save() {
        firstBilling.setBillingId(null);
        Integer result = jdbcBillingRepository.save(firstBilling);
        Assertions.assertEquals(1, result);
    }

    @Test
    void saveBatch() {
        Billing secondBilling = new Billing();
        secondBilling.setAmount(BigDecimal.TEN);
        firstBilling.setBillingId(null);
        List<Billing> billings = Arrays.asList(firstBilling, secondBilling);

        int[] result = jdbcBillingRepository.save(billings);

        Assertions.assertArrayEquals(new int[]{1, 1}, result);
    }

    @Test
    void update() {
        firstBilling.setBillingId(null);
        firstBilling.setAmount(BigDecimal.ONE);

        Integer result = jdbcBillingRepository.update(firstBilling, 1L);

        Assertions.assertEquals(1, result);
    }

    @Test
    void delete() {
        Integer result = jdbcBillingRepository.delete(1L);

        Assertions.assertEquals(1, result);
    }
}