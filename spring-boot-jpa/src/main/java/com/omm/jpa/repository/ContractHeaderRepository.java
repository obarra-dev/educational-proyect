package com.omm.jpa.repository;

import com.omm.jpa.model.entity.ContractHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ContractHeaderRepository extends JpaRepository<ContractHeader, Long> {
}
