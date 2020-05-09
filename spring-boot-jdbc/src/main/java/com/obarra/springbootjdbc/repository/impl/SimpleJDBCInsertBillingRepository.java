package com.obarra.springbootjdbc.repository.impl;

import com.obarra.springbootjdbc.model.Billing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SimpleJDBCInsertBillingRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SimpleJDBCInsertBillingRepository(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(final Billing billing) {
        final Map<String, Object> parameter = new HashMap<>();
        parameter.put("policy_id", billing.getPolicyId());
        parameter.put("amount", billing.getPolicyId());

        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("BILLING")
                .usingGeneratedKeyColumns("billing_id");

        simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(billing)).longValue();

        return simpleJdbcInsert.executeAndReturnKey(parameter).longValue();
    }

    public Long saveComplete(final Billing billing) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("BILLING")
                .usingGeneratedKeyColumns("billing_id");

        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(billing)).longValue();
    }
}
