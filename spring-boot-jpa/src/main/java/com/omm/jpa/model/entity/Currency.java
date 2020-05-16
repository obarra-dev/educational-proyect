package com.omm.jpa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {
    @Id
    private Long currencyId;
    @Column(name = "CURRENCY_DESC")
    private String description;
    @Column(name = "CURRENCY_MNEMONIC")
    private String mnemonic;
    @Column(name = "CURRENCY_THOUSAND_SEPARATOR")
    private String thousandSeparator;
    @Column(name = "CURRENCY_DECIMAL_SEPARATOR")
    private String decimalSeparator;

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getThousandSeparator() {
        return thousandSeparator;
    }

    public void setThousandSeparator(String thousandSeparator) {
        this.thousandSeparator = thousandSeparator;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }
}