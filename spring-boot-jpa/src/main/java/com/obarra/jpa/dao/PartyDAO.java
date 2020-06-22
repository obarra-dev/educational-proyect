package com.obarra.jpa.dao;

import com.obarra.jpa.dto.PartyDTO;
import com.obarra.jpa.model.entity.Party;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PartyDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<PartyDTO> getParties() {
        List<PartyDTO> parties = entityManager.createNamedQuery("getPartyNative", PartyDTO.class)
                .getResultList();
        return parties;
    }

    public Long save(Party party) {
        entityManager.persist(party);
        return party.getPartyId();

    }

    public Integer saveNativeQuery(Party party) {
        Query query = entityManager.createNativeQuery("insert into party (first_name, last_name) values (?, ?)");

        query.setParameter(1, party.getFirstName())
                .setParameter(2, party.getLastName());
        return query.executeUpdate();
    }
}
