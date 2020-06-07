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
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class PaymentTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentTermId;
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "party_id", insertable = true, updatable = true)
    private Party party;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;
    @ManyToOne(optional = true)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column
    private Long creditCardId;
    @Column
    private String accountNbr;
    @Column
    private String interAccountNbr;
    @Column
    private LocalDate expiration;
    @Column
    private String paymentKey;
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

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentTerm that = (PaymentTerm) o;
        return Objects.equals(paymentTermId, that.paymentTermId) &&
                Objects.equals(party, that.party) &&
                Objects.equals(paymentType, that.paymentType) &&
                Objects.equals(creditCardId, that.creditCardId) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(accountNbr, that.accountNbr) &&
                Objects.equals(interAccountNbr, that.interAccountNbr) &&
                Objects.equals(expiration, that.expiration) &&
                Objects.equals(paymentKey, that.paymentKey) &&
                Objects.equals(bank, that.bank) &&
                Objects.equals(bankBranchId, that.bankBranchId) &&
                Objects.equals(periodId, that.periodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentTermId, party, paymentType, creditCardId, currency, accountNbr, interAccountNbr, expiration, paymentKey, bank, bankBranchId, periodId);
    }
}
