package com.obarra.springbootjdbc.repository.impl;

import com.obarra.springbootjdbc.model.Billing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SimpleJDBCInsertBillingRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SimpleJDBCInsertBillingRepository(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(final Long policyId, final BigDecimal amount) {
        final Map<String, Object> parameter = new HashMap<>();
        parameter.put("policy_id", policyId);
        parameter.put("amount", amount);

        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("BILLING")
                .usingGeneratedKeyColumns("billing_id");

        return simpleJdbcInsert.executeAndReturnKey(parameter).longValue();
    }

    public Long saveComplete(final Billing billing) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("BILLING")
                .usingGeneratedKeyColumns("billing_id");

        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(billing)).longValue();
    }
}
