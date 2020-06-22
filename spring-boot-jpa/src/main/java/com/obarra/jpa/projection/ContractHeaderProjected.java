package com.obarra.jpa.projection;

import java.time.LocalDateTime;

public interface ContractHeaderProjected {
    Long getContractId();
    LocalDateTime getContractFrom();
    LocalDateTime getContractTo();
}
