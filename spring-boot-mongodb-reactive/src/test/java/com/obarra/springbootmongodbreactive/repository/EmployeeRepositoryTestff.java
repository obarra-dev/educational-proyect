package com.obarra.springbootmongodbreactive.repository;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import  org.assertj.core.api.Assertions;


@ExtendWith(SpringExtension.class)
@DataMongoTest
class EmployeeRepositoryTestff {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void simpleTest() {
        DBObject dbObject = BasicDBObjectBuilder.start().add("name", "omar").get();

        mongoTemplate.save(dbObject, "collection");

        List<DBObject> result = mongoTemplate.findAll(DBObject.class, "collection");
        Assertions.assertThat(result)
                .extracting("name")
                .containsOnly("omar");
    }
}