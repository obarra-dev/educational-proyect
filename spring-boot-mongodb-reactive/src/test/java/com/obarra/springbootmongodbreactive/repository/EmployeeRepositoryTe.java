package com.obarra.springbootmongodbreactive.repository;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongoConfig;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

//https://www.baeldung.com/spring-boot-embedded-mongodb
class EmployeeRepositoryTe {

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() throws Exception {
        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net("localhost", 27017, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();

        mongoTemplate = new MongoTemplate(new MongoClient("localhost", 27017), "test");
    }

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