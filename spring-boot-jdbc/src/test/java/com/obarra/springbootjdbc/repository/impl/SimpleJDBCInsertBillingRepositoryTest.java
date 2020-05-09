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

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@ActiveProfiles("integrationtest")
class SimpleJDBCInsertBillingRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJDBCInsertBillingRepository simpleJDBCInsertBillingRepository;

    @BeforeEach
    void setUp() {
        simpleJDBCInsertBillingRepository = new SimpleJDBCInsertBillingRepository(jdbcTemplate);
    }

    @Test
    void save() {
        Long billingId = simpleJDBCInsertBillingRepository.save(1L, BigDecimal.TEN);

        Assertions.assertNotNull(billingId);
        Assertions.assertTrue(billingId > 1);
    }

    @Test
    void saveComplete() {
        Long billingId = simpleJDBCInsertBillingRepository.saveComplete(new Billing());

        Assertions.assertNotNull(billingId);
        Assertions.assertTrue(billingId > 1);
    }
}