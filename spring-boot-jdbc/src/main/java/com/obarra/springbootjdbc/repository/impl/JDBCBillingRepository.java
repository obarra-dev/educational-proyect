package com.obarra.springbootjdbc.repository.impl;

import com.obarra.springbootjdbc.model.Billing;
import com.obarra.springbootjdbc.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JDBCBillingRepository implements BillingRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCBillingRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer count() {
        return jdbcTemplate.queryForObject("select count(1) from BILLING", Integer.class);
    }

    @Override
    public Optional<Billing> findById(final Long billingId) {
        return jdbcTemplate.queryForObject("select * from BILLING where billing_id = ?",
                new Object[]{billingId},
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
        return jdbcTemplate.query("select * from BILLING",
                (rs, rowNum) -> new Billing(rs.getLong("billing_id"),
                        rs.getLong("policy_id"),
                        rs.getLong("billing_type_id"),
                        null,
                        rs.getBigDecimal("amount"))
        );
    }

    @Override
    public Integer save(final Billing billing) {
        return jdbcTemplate.update("insert into BILLING(policy_id, billing_type_id, create_date, amount) "
                + "(?, ?, ?, ?)",
                billing.getPolicyId(),
                billing.getBillingTypeId(),
                billing.getCreateDate(),
                billing.getAmount());
    }

    @Override
    public Integer update(final Billing billing, final Integer billingId) {
        return jdbcTemplate.update("update BILLING"
                        + " set policy_id = ?, billing_type_id = ?, amount = ? "
                        + " where billing_id = ?",
                billing.getPolicyId(),
                billing.getBillingTypeId(),
                billing.getAmount(),
                billingId
                );
    }

    @Override
    public Integer delete(final Integer billingId) {
        return jdbcTemplate.update("delete from BILLING where billing_id = ?",
                billingId);
    }
}
