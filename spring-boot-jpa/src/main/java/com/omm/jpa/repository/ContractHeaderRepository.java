package com.omm.jpa.repository;

import com.omm.jpa.model.entity.ContractHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ContractHeaderRepository extends JpaRepository<ContractHeader, Long> {

    List<ContractHeader> findDistinctByAgencyIdNotIn(List<Long> agencyIds);

    List<Long> findInsurerIdByAgencyIdNotIn(List<Long> agencyIds);

    List<ContractHeader> findInsurerIdDistinctByAgencyIdIsNotNull();

    List<ContractHeader> findByAgencyIdIsNotNull();


    @Query("select c from ContractHeader c where :coveragePlanId is null or  c.coveragePlanId=:coveragePlanId")
    Page<ContractHeader> findByCoveragePlanIdJPQL(@Param("coveragePlanId") Long coveragePlanId, Pageable pageRequest);

    Page<ContractHeader> findByCoveragePlanId(Long coveragePlanId, Pageable pageRequest);
}
