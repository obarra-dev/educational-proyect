package com.obarra.webflux.model;

public class CurrencyExchange {
    private String currency;
    private Double sellerValue;

    public CurrencyExchange(String currency, Double sellerValue) {
        this.currency = currency;
        this.sellerValue = sellerValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getSellerValue() {
        return sellerValue;
    }

    public void setSellerValue(Double sellerValue) {
        this.sellerValue = sellerValue;
    }
}
