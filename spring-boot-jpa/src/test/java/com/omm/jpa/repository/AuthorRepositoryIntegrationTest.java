package com.omm.jpa.repository;

import com.omm.jpa.model.entity.Author;
import com.sun.deploy.si.SingleInstanceManager;
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

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AuthorRepositoryIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Before
    public void before(){
        entityManager.persist(new Author("Omar", "Barra"));
        entityManager.persist(new Author("Mariela", "Barra"));
    }

    @Test
    public void findByFirstName(){
     Author authorExpected = new Author("Omar", "Barra");
     List<Author> result = authorRepository.findByFirstName("Omar");
     MatcherAssert.assertThat(result, Matchers.contains(authorExpected));
    }

    @Test
    public void findByFirstNameNot(){
        Author authorExpected = new Author("Omar", "Barra");
        List<Author> result = authorRepository.findByFirstName("omar");
        MatcherAssert.assertThat(result, IsNot.not(Matchers.contains(authorExpected)));
    }

    @Test
    public void findByFirstNameIgnoreCase(){
     Author authorExpected = new Author("Omar", "Barra");
     List<Author> result = authorRepository.findByFirstNameIgnoreCase("Omar");
     MatcherAssert.assertThat(result, Matchers.contains(authorExpected));
    }

    @Test
    public void findByFirstNameIgnoreCaseNot(){
        Author authorExpected = new Author("Omar", "Barra");
        List<Author> result = authorRepository.findByFirstNameIgnoreCase("omar");
        MatcherAssert.assertThat(result, Matchers.contains(authorExpected));
    }


}