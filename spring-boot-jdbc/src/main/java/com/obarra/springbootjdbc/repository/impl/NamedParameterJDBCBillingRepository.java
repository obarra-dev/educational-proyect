package com.obarra.springbootjdbc.repository.impl;

import com.obarra.springbootjdbc.repository.mapper.BillingMapper;
import com.obarra.springbootjdbc.model.Billing;
import com.obarra.springbootjdbc.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class NamedParameterJDBCBillingRepository implements BillingRepository {
    private static final String SQL_INSERT = "insert into BILLING("
            + " policy_id,"
            + " billing_type_id,"
            + " create_date, amount) "
            + " values (:policyId, :billingTypeId, :createDate, :amount)";

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
                BillingMapper::resultMapOneOptional);
    }

    @Override
    public List<Billing> findAll() {
        return namedParameterJdbcTemplate
                .query("select * from BILLING",
                        BillingMapper::resultMapOne);
    }

    @Override
    public Long saveAndReturnId(final Billing billing) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate
                .update(SQL_INSERT,
                        new BeanPropertySqlParameterSource(billing),
                        keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Integer save(final Billing billing) {
        return namedParameterJdbcTemplate
                .update(SQL_INSERT,
                        new BeanPropertySqlParameterSource(billing));
    }

    @Override
    public int[] save(final List<Billing> billings) {
        SqlParameterSource[] insertBatch = SqlParameterSourceUtils.createBatch(billings.toArray());
        return namedParameterJdbcTemplate.batchUpdate(SQL_INSERT, insertBatch);
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
