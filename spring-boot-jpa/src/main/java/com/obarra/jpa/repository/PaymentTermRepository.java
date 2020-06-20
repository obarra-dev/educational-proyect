package com.obarra.jpa.repository;

import com.obarra.jpa.dto.PaymentTermDTO;
import com.obarra.jpa.model.entity.Party;
import com.obarra.jpa.model.entity.PaymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Long> {

    @Query("select pt from PaymentTerm pt where pt.party.firstName = :firstName")
    List<PaymentTerm> findByPartyFirstName(@Param("firstName") String firstName);

    @Query("select pt.party from PaymentTerm pt where pt.paymentTermId = :id")
    Party findPartyByPaymentTermId(@Param("id") Long id);

    @Query("select new com.obarra.jpa.dto.PaymentTermDTO(pt.bank.description, "
            + "pt.paymentType.description, "
            + "pt.currency.description) "
            + "from PaymentTerm pt "
            + "where pt.paymentTermId = :id")
    PaymentTermDTO findPaymentTermDTOById(@Param("id") Long id);

    List<PaymentTerm> findByParty_FirstName_AndParty_LastName(String firstName, String lastName);

}
