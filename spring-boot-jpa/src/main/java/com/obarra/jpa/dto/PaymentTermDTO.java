package com.obarra.jpa.dto;


public class PaymentTermDTO {
    private String bankDescription;
    private String paymentTermTypeDescription;
    private String currencyDescription;
    private Long number;

    public PaymentTermDTO(String bankDescription, String paymentTermTypeDescription, String currencyDescription, Long number) {
        this.bankDescription = bankDescription;
        this.paymentTermTypeDescription = paymentTermTypeDescription;
        this.currencyDescription = currencyDescription;
        this.number = number;
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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PaymentTermDTO{" +
                "bankDescription='" + bankDescription + '\'' +
                ", paymentTermTypeDescription='" + paymentTermTypeDescription + '\'' +
                ", currencyDescription='" + currencyDescription + '\'' +
                ", number=" + number +
                '}';
    }
}
