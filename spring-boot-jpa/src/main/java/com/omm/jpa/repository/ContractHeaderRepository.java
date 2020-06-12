package com.omm.jpa.repository;

import com.omm.jpa.model.entity.ContractHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ContractHeaderRepository extends JpaRepository<ContractHeader, Long> {

    List<ContractHeader> findDistinctByAgencyIdNotIn(List<Long> agencyIds);

    List<Long> findInsurerIdByAgencyIdNotIn(List<Long> agencyIds);

    List<ContractHeader> findInsurerIdDistinctByAgencyIdIsNotNull();

    List<ContractHeader> findByAgencyIdIsNotNull();

}
