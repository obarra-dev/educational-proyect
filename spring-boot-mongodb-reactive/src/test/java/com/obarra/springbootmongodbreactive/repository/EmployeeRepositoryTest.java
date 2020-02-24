package com.obarra.springbootmongodbreactive.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class EmployeeRepositoryTest {

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    void findByNameAndSalary() {
        DBObject dbObject = BasicDBObjectBuilder.start().add("name", "omar").get();

        reactiveMongoTemplate.save(dbObject, "collection");

        Flux<DBObject> result = reactiveMongoTemplate.findAll(DBObject.class, "collection");


        StepVerifier.create(result)
                .assertNext(employee -> {
                    assertEquals("omar", employee.get("name"));
                })
                .expectComplete()
                .verify();


        //Flux<DBObject> result = Flux.just(dbObject);

               // result.map(s -> s.get("name")).subscribe(System.out::println);

    }
}