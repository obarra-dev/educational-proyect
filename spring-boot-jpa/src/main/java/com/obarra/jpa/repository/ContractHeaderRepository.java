package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.ContractHeader;
import com.obarra.jpa.projection.ContractHeaderProjected;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    Slice<ContractHeader> findByAgencyId(Long agencyId, Pageable pageRequest);

    List<ContractHeader> findByInsurerId(Long insurerId, Pageable pageRequest);

    @Query(value = "select c.CONTRACT_ID,"
            + " c.CONTRACT_FROM "
            + " from CONTRACT_HEADER c "
            + " where :coveragePlanId is null or  c.COVERAGE_PLAN_ID=:coveragePlanId",
    nativeQuery = true)
    List<Object[]> findByCoveragePlanIdSQL(@Param("coveragePlanId") Long coveragePlanId);

    @Query(value = "select c.CONTRACT_ID,"
            + " c.CONTRACT_FROM "
            + " from CONTRACT_HEADER c "
            + " where :coveragePlanId is null or  c.COVERAGE_PLAN_ID=:coveragePlanId",
            nativeQuery = true,
            countQuery = " select count(CONTRACT_ID) from CONTRACT_HEADER"
    )
    Page<Object[]> findByCoveragePlanIdPageableSQL(@Param("coveragePlanId") Long coveragePlanId, Pageable pageRequest);


    List<ContractHeader> findByContractToGreaterThanEqual(LocalDateTime contractTo);

    @Query("select c from ContractHeader c where c.contractFrom >= :contractFrom and c.contractTo <= :contractTo ")
    List<ContractHeader> findByRange(@Param("contractFrom") LocalDateTime contractFrom,
                                     @Param("contractTo") LocalDateTime contractTo);

    @Query(value = "select CONTRACT_ID as contractId, CONTRACT_FROM as contractFrom, CONTRACT_TO as contractTo from CONTRACT_HEADER", nativeQuery = true)
    List<ContractHeaderProjected> findAllProjected();
}
