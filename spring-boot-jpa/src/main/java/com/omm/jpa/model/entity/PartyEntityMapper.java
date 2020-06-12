package com.omm.jpa.model.entity;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import java.time.LocalDate;

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
@Entity
public class PartyEntityMapper {
    @Id
    private Long id;
}
