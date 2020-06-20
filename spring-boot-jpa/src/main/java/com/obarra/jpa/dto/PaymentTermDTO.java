package com.obarra.jpa.dto;

import java.math.BigDecimal;

public class PaymentTermDTO {
    private String bankDescription;
    private String paymentTermTypeDescription;
    private String currencyDescription;

    public PaymentTermDTO(String bankDescription, String paymentTermTypeDescription, String currencyDescription) {
        this.bankDescription = bankDescription;
        this.paymentTermTypeDescription = paymentTermTypeDescription;
        this.currencyDescription = currencyDescription;
    }

    public String getBankDescription() {
        return bankDescription;
    }

    public void setBankDescription(String bankDescription) {
        this.bankDescription = bankDescription;
    }

    public String getPaymentTermTypeDescription() {
        return paymentTermTypeDescription;
    }

    public void setPaymentTermTypeDescription(String paymentTermTypeDescription) {
        this.paymentTermTypeDescription = paymentTermTypeDescription;
    }

    public String getCurrencyDescription() {
        return currencyDescription;
    }

    public void setCurrencyDescription(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }
}
