package com.omm.jpa.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class PaymentTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentTermId;
    @Column
    private Long partyId;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;
    @Column
    private Long creditCardId;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;
    @Column
    private String accountNbr;
    @Column
    private String interAccountNbr;
    @Column
    private LocalDate expiration;
    @Column
    private String paymentKey;
    @ManyToOne(optional = true)
    @JoinColumn(name = "bank_id")
    private Bank bank;
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
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
