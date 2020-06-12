package com.omm.jpa.model.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity

@SqlResultSetMapping(
        name="ScheduleResult",
        classes={
                @ConstructorResult(
                        targetClass=com.omm.jpa.dto.PartyDTO.class,
                        columns={
                                @ColumnResult(name="first_name", type=String.class),
                                @ColumnResult(name="last_name", type=String.class),
                                @ColumnResult(name="expiration", type= LocalDate.class),
                                @ColumnResult(name="currency_id", type= Long.class)
                        })})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getPartyNative",
                query = "select p.first_name, p.last_name, t.expiration, c.currency_id from party p "
                        + "inner join payment_term t on t.party_id = p.party_id "
                        + "inner join currency c on c.currency_id = t.currency_id",
                resultSetMapping = "ScheduleResult"
        )
})
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long partyId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "party", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PaymentTerm> paymentTerms;

    public Party() {
    }

    public Party(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<PaymentTerm> getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(Set<PaymentTerm> paymentTerms) {
        this.paymentTerms = paymentTerms;
    }
}
