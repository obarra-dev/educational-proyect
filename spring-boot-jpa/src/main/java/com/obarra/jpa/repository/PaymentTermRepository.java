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

    @Query("select pt "
            + "from PaymentTerm pt inner join pt.party p "
            + "where p.firstName = :firstName")
    List<PaymentTerm> findByPartyFirstNameWithJoin(@Param("firstName") String firstName);

    @Query("select pt "
            + "from PaymentTerm pt inner join Party p on p.partyId = pt.party.partyId "
            + "where p.firstName = :firstName")
    List<PaymentTerm> findByPartyFirstNameWithNormalJoin(@Param("firstName") String firstName);


    List<PaymentTerm> findByParty(Party party);

    @Query("select pt.party from PaymentTerm pt where pt.paymentTermId = :id")
    Party findPartyByPaymentTermId(@Param("id") Long id);

    @Query("select new com.obarra.jpa.dto.PaymentTermDTO(pt.bank.description, "
            + "pt.paymentType.description, "
            + "pt.currency.description) "
            + "from PaymentTerm pt "
            + "where pt.paymentTermId = :id")
    PaymentTermDTO findPaymentTermDTOById(@Param("id") Long id);

    List<PaymentTerm> findByParty_FirstName_AndParty_LastName(String firstName, String lastName);

    @Query("select case when count(1) > 0 then true else false end "
            + "from PaymentTerm p where p.creditCardId is null")
    Boolean existWhitOutCreditCardId();

    Boolean existsByCreditCardIdIsNull();

}
