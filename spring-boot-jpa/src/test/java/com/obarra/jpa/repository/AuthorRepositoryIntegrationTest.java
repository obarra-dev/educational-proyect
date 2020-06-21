package com.obarra.jpa.repository;

import com.obarra.jpa.model.entity.Party;
import com.obarra.jpa.model.entity.AuthorProjected;
import com.obarra.jpa.model.entity.AuthorSimplified;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AuthorRepositoryIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PartyRepository authorRepository;

    @Autowired
    private AuthorNativeRepository authorComplexRepository;

    @Autowired
    private AuthorNativeEntityRepository authorNativeEntityRepository;


    @Before
    public void before(){
        entityManager.persist(new Party("Omar", "Barra"));
        entityManager.persist(new Party("Mariela", "Barra"));
    }

    @Test
    public void findByFirstName(){
        List<Party> result = authorRepository.findByFirstName("Omar");
        MatcherAssert.assertThat(result, Matchers.<Party>hasSize(1));
    }

    @Test
    public void findByFirstNameNot(){
        Party authorExpected = new Party("Omar", "Barra");
        List<Party> result = authorRepository.findByFirstName("omar");
        MatcherAssert.assertThat(result, IsNot.not(Matchers.contains(authorExpected)));
    }

    @Test
    public void findByFirstNameIgnoreCase(){
     Party authorExpected = new Party("Omar", "Barra");
     List<Party> result = authorRepository.findByFirstNameIgnoreCase("Omar");
     MatcherAssert.assertThat(result, Matchers.contains(authorExpected));
    }

    @Test
    public void findByFirstNameIgnoreCaseNot(){
        Party authorExpected = new Party("Omar", "Barra");
        List<Party> result = authorRepository.findByFirstNameIgnoreCase("omar");
        MatcherAssert.assertThat(result, Matchers.contains(authorExpected));
    }
    @Test
    public void findTest(){
        List<AuthorSimplified> authors = authorNativeEntityRepository.executeQuery();
        for (AuthorSimplified authorSimplified: authors) {
            System.out.println(authorSimplified.getName() +" "+authorSimplified.getSurname());
        }

    }

    @Test
    public void find(){

        List<AuthorProjected> authors = authorComplexRepository.findFirstByFirstName();
        for (AuthorProjected p: authors) {
            System.out.println(p.getName() +" "+p.getSurname());
        }
        String na = authors.get(0).getName();
        System.out.println(na);


        /**
        List<Object> authors = authorComplexRepository.findFirstByFirstName();
        for(Object object: authors){
            System.out.println(object);
        }

         **/
    }

}