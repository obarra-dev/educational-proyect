package com.omm.jpa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity(name = "derderder")
@Table(name = "PAYMENT_TERM")
public class PaymentTermPlain {

    @Id
    private Long paymentTermId;
    @Column(name = "PARTY_ID")
    private Long partyId;
    @Column(name = "PAYMENT_TYPE_ID")
    private Long paymentTypeId;
    @Column
    private Long creditCardId;
    @Column(name = "CURRENCY_ID")
    private Long currencyId;
    @Column
    private String accountNbr;
    @Column
    private String interAccountNbr;
    @Column
    private LocalDate expiration;
    @Column
    private String paymentKey;
    @Column(name = "BANK_ID")
    private Long bankId;
    @Column
    private Long bankBranchId;
    @Column
    private Long periodId;

    public Long getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId(Long paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getAccountNbr() {
        return accountNbr;
    }

    public void setAccountNbr(String accountNbr) {
        this.accountNbr = accountNbr;
    }

    public String getInterAccountNbr() {
        return interAccountNbr;
    }

    public void setInterAccountNbr(String interAccountNbr) {
        this.interAccountNbr = interAccountNbr;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public String getPaymentKey() {
        return paymentKey;
    }

    public void setPaymentKey(String paymentKey) {
        this.paymentKey = paymentKey;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(Long bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }
}
