package com.omm.jpa.dto;


import java.time.LocalDate;

public class PartyDTO {
    private String name;
    private String surName;
    private LocalDate localDate;
    private Long currency;

    public PartyDTO(String name, String surName, LocalDate localDate, Long currency) {
        this.name = name;
        this.surName = surName;
        this.localDate = localDate;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Long getCurrency() {
        return currency;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }
}
