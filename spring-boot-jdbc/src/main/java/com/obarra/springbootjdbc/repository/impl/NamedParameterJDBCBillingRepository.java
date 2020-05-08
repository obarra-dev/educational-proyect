package com.obarra.springbootjdbc.repository.impl;

import com.obarra.springbootjdbc.model.Billing;
import com.obarra.springbootjdbc.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class NamedParameterJDBCBillingRepository implements BillingRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public NamedParameterJDBCBillingRepository(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Long count() {
        return namedParameterJdbcTemplate
                .queryForObject("select count(1) from BILLING",
                        new HashMap<>(), Long.class) ;
    }

    @Override
    public Optional<Billing> findById(final Long billingId) {
        return namedParameterJdbcTemplate.queryForObject("select * from BILLING where billing_id = :billingId",
                new MapSqlParameterSource("billingId", billingId),
                (rs, rowNum) -> Optional.of(
                        new Billing(rs.getLong("billing_id"),
                                rs.getLong("policy_id"),
                                rs.getLong("billing_type_id"),
                                rs.getDate("create_date").toLocalDate(),
                                rs.getBigDecimal("amount"))
                ));
    }

    @Override
    public List<Billing> findAll() {
        return namedParameterJdbcTemplate.query("select * from BILLING",
                (rs, rowNum) -> new Billing(rs.getLong("billing_id"),
                        rs.getLong("policy_id"),
                        rs.getLong("billing_type_id"),
                        rs.getDate("create_date").toLocalDate(),
                        rs.getBigDecimal("amount"))
        );
    }

    @Override
    public Integer save(final Billing billing) {
        return namedParameterJdbcTemplate
                .update("insert into BILLING(billing_id, policy_id, billing_type_id, create_date, amount) "
                                + " values (:billingId, :policyId, :billingTypeId, :createDate, :amount)",
                        new BeanPropertySqlParameterSource(billing));
    }

    @Override
    public Integer update(final Billing billing, final Long billingId) {
        billing.setBillingId(billingId);
        return namedParameterJdbcTemplate.update("update BILLING"
                        + " set policy_id = :policyId, billing_type_id = :billingTypeId, amount = :amount "
                        + " where billing_id = :billingId",
                new BeanPropertySqlParameterSource(billing)
                );
    }

    @Override
    public Integer delete(final Long billingId) {
        return namedParameterJdbcTemplate.update("delete from BILLING where billing_id = :billingId",
                new MapSqlParameterSource("billingId", billingId));
    }
}
