package com.obarra.jpa.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ContractHeader {
    @Id
    private Long contractId;
    private Long agencyId;
    private Long insurerId;
    private Long insuredPartyId;
    private Long holderPartyId;
    private Long coveragePlanId;
    private LocalDateTime contractFrom;
    private LocalDateTime contractTo;
    private Long paymentPlanId;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
    }

    public Long getInsuredPartyId() {
        return insuredPartyId;
    }

    public void setInsuredPartyId(Long insuredPartyId) {
        this.insuredPartyId = insuredPartyId;
    }

    public Long getHolderPartyId() {
        return holderPartyId;
    }

    public void setHolderPartyId(Long holderPartyId) {
        this.holderPartyId = holderPartyId;
    }

    public Long getCoveragePlanId() {
        return coveragePlanId;
    }

    public void setCoveragePlanId(Long coveragePlanId) {
        this.coveragePlanId = coveragePlanId;
    }

    public LocalDateTime getContractFrom() {
        return contractFrom;
    }

    public void setContractFrom(LocalDateTime contractFrom) {
        this.contractFrom = contractFrom;
    }

    public LocalDateTime getContractTo() {
        return contractTo;
    }

    public void setContractTo(LocalDateTime contractTo) {
        this.contractTo = contractTo;
    }

    public Long getPaymentPlanId() {
        return paymentPlanId;
    }

    public void setPaymentPlanId(Long paymentPlanId) {
        this.paymentPlanId = paymentPlanId;
    }
}
