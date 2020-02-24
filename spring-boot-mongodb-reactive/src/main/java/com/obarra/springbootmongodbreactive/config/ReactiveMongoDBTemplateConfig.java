package com.obarra.springbootmongodbreactive.config;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

//@Configuration
public class ReactiveMongoDBTemplateConfig {

    @Value("${database.name}")
    private String dataBaseName;

    @Autowired
    MongoClient mongoClient;

    public ReactiveMongoTemplate reactiveMongoTemplate(){
        return new ReactiveMongoTemplate(mongoClient, dataBaseName);
    }
}
