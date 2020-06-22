package com.obarra.jpa.repository;

import com.obarra.jpa.projection.PartyProjected;
import com.obarra.jpa.model.entity.Party;
import com.obarra.jpa.model.entity.PaymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findByFirstName(String firstName);

    List<Party> findByFirstNameIgnoreCase(String firstName);

    List<Party> findByPaymentTerms(List<PaymentTerm> paymentTerms);

    List<Party> findByPaymentTermsIn(List<PaymentTerm> paymentTerms);

    @Query("select p from Party p inner join p.paymentTerms t where t.paymentTermId in :paymentTermIds")
    List<Party> findByPaymentTermIds(@Param("paymentTermIds") List<Long> paymentTermIds);

    @Query("select p from Party p inner join p.paymentTerms t where t.paymentTermId in :paymentTermIds")
    List<Party> findByPaymentTermIds(@Param("paymentTermIds") Long[] paymentTermIds);

    @Query(value = "select  first_name as name, last_name as surname from PARTY where first_name = :firstName",
            nativeQuery = true)
    List<PartyProjected> findAllProjected(@Param("firstName") String firstName);

}
