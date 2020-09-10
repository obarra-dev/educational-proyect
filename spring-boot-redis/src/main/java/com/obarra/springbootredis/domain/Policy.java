package com.obarra.springbootredis.domain;

import java.io.Serializable;
import java.util.Objects;

public class Policy implements Serializable {

    private Long policyId;
    private String note;

    public Policy() {

    }

    public Policy(Long policyId, String note) {
        this.policyId = policyId;
        this.note = note;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(policyId, policy.policyId) &&
                Objects.equals(note, policy.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyId, note);
    }
}
