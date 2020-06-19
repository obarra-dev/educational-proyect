package com.omm.jpa.dao;

import com.omm.jpa.dto.PartyDTO;
import com.omm.jpa.model.entity.Party;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
