package com.obarra.springbootjdbc.repository.mapper;

import com.obarra.springbootjdbc.model.Billing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class BillingMapper {

    public static Optional<Billing> resultMapOneOptional(final ResultSet rs, final int rowNum) throws SQLException {
        return  Optional.of(resultMapOne(rs, rowNum));
    }

    public static Billing resultMapOne(final ResultSet rs, final int rowNum) throws SQLException {
        final Billing billing = new Billing();
        billing.setBillingId(rs.getLong("billing_id"));
        if(Objects.nonNull(rs.getObject("policy_id"))) {
            billing.setPolicyId(rs.getLong("policy_id"));
        }

        if(Objects.nonNull(rs.getObject("billing_type_id"))) {
            billing.setPolicyId(rs.getLong("billing_type_id"));
        }

        billing.setAmount(rs.getBigDecimal("amount"));

        if(Objects.nonNull(rs.getDate("create_date"))) {
            billing.setCreateDate(rs.getDate("create_date").toLocalDate());
        }

        return billing;
    }
}
