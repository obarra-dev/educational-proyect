package com.omm.jpa.repository;

import com.omm.jpa.model.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findByFirstName(String firstName);

    List<Party> findByFirstNameIgnoreCase(String firstName);
}
