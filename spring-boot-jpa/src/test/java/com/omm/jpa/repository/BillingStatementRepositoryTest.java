package com.omm.jpa.repository;

import com.omm.jpa.model.entity.Author;
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

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BillingStatementRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;


    //@Before
    public void setUp() throws Exception {
        Author author = new  Author("Mariela", "Barra");
        entityManager.persist(author);
        System.out.println(author.getId());



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
    public void test() {
        Query queryE = entityManager.createNativeQuery("select top 3 * from BILLING_STATEMENT ");
        queryE.getResultList();
    }


}