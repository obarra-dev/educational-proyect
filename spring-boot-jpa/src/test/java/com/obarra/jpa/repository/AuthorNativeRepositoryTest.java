package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.Party;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AuthorNativeRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AuthorNativeRepository authorNativeRepository;

    @Before
    public void setUp() throws Exception {
        Party author = new Party("Mariela", "Barra");
        entityManager.persist(author);
        System.out.println(author.getPartyId());
        author = new Party("Mariela", "Barra");
        entityManager.persist(author);
        System.out.println(author.getPartyId());



        Query queryE = entityManager.createNativeQuery("insert into author (first_name, last_name) values (?, ?)");

        queryE.setParameter(1, "Maru")
                .setParameter(2, "Think");
       queryE.executeUpdate();
        //TODO buscar la forma de recuperar el id
       Object der = queryE.getSingleResult();
        //System.out.println(der);
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findFirstByFirstName() {

    }

    @Test
    public void findAll() {
        List<Object> result = authorNativeRepository.findAll();
        System.out.println(result);

        Integer numberOfUpdatedEntities = authorNativeRepository.deleteAllOmar();


        result = authorNativeRepository.findAll();
        System.out.println(result);
        assertEquals(Integer.valueOf(1), numberOfUpdatedEntities);
    }

    @Test
    public void deleteAllOmar() {
        List<Object> der = authorNativeRepository.findAll();
        assertEquals(Long.valueOf(2), authorNativeRepository.count());

        Integer numberOfUpdatedEntities = authorNativeRepository.deleteAllOmar();
        assertEquals(Integer.valueOf(1), numberOfUpdatedEntities);
        assertEquals(Long.valueOf(1), authorNativeRepository.count());
    }
}