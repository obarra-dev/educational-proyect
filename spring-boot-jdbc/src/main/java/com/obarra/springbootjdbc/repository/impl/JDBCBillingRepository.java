package com.obarra.springbootjdbc.repository.impl;

import com.obarra.springbootjdbc.model.Billing;
import com.obarra.springbootjdbc.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
    public Long count() {
        return jdbcTemplate.queryForObject("select count(1) from BILLING", Long.class);
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
                        rs.getDate("create_date").toLocalDate(),
                        rs.getBigDecimal("amount"))
        );
    }

    @Override
    public Long saveAndReturnId(final Billing billing) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "insert into BILLING(billing_id, "
                                    + "policy_id, "
                                    + "billing_type_id, "
                                    + "create_date, amount) "
                                    + "values (?, ?, ?, ?, ?)",
                            new String[]{"billing_id"});
                    preparedStatement.setObject(1, billing.getBillingId());
                    preparedStatement.setObject(2, billing.getPolicyId());
                    preparedStatement.setObject(3, billing.getBillingTypeId());
                    preparedStatement.setObject(4, java.sql.Date.valueOf(billing.getCreateDate()));
                    preparedStatement.setBigDecimal(5, billing.getAmount());
                    return preparedStatement;
                },
                keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Integer save(final Billing billing) {
        return jdbcTemplate
                .update("insert into BILLING(billing_id, policy_id, billing_type_id, create_date, amount) "
                                + " values (?, ?, ?, ?, ?)",
                        billing.getBillingId(),
                        billing.getPolicyId(),
                        billing.getBillingTypeId(),
                        billing.getCreateDate(),
                        billing.getAmount());
    }

    @Override
    public Integer update(final Billing billing, final Long billingId) {
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
    public Integer delete(final Long billingId) {
        return jdbcTemplate.update("delete from BILLING where billing_id = ?",
                billingId);
    }
}
