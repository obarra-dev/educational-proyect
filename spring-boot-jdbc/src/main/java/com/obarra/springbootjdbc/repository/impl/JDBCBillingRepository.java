package com.obarra.springbootjdbc.repository.impl;

import com.obarra.springbootjdbc.repository.mapper.BillingMapper;
import com.obarra.springbootjdbc.model.Billing;
import com.obarra.springbootjdbc.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBCBillingRepository implements BillingRepository {

    private static final String SQL_INSERT = "insert into BILLING(policy_id,"
            + " billing_type_id,"
            + " create_date,"
            + " amount) "
            + " values (?, ?, ?, ?)";

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
                BillingMapper::resultMapOneOptional);
    }

    @Override
    public List<Billing> findAll() {
        return jdbcTemplate
                .query("select * from BILLING",
                        BillingMapper::resultMapOne);
    }

    @Override
    public Long saveAndReturnId(final Billing billing) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            SQL_INSERT,
                            new String[]{"billing_id"});
                    preparedStatement.setObject(1, billing.getPolicyId());
                    preparedStatement.setObject(2, billing.getBillingTypeId());
                    preparedStatement.setObject(3, java.sql.Date.valueOf(billing.getCreateDate()));
                    preparedStatement.setBigDecimal(4, billing.getAmount());
                    return preparedStatement;
                },
                keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Integer save(final Billing billing) {
        return jdbcTemplate
                .update(SQL_INSERT,
                        billing.getPolicyId(),
                        billing.getBillingTypeId(),
                        billing.getCreateDate(),
                        billing.getAmount());
    }

    @Override
    public int[] save(final List<Billing> billings) {
        return jdbcTemplate.batchUpdate(SQL_INSERT,
                new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Billing billing = billings.get(i);
                preparedStatement.setObject(1, billing.getPolicyId());
                preparedStatement.setObject(2, billing.getBillingTypeId());
                preparedStatement.setObject(3, java.sql.Date.valueOf(billing.getCreateDate()));
                preparedStatement.setBigDecimal(4, billing.getAmount());
            }

            @Override
            public int getBatchSize() {
                return billings.size();
            }
        });
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
