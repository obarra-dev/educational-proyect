package com.obarra.springbootjdbc.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Billing {
    private Long billingId;
    private Long policyId;
    private Long billingTypeId;
    private LocalDate createDate;
    private BigDecimal amount;

    public Billing(final Long billingId,
                   final Long policyId,
                   final Long billingTypeId,
                   final LocalDate createDate,
                   final BigDecimal amount) {
        this.billingId = billingId;
        this.policyId = policyId;
        this.billingTypeId = billingTypeId;
        this.createDate = createDate;
        this.amount = amount;
    }
}
