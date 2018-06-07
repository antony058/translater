package ru.priamosudov.restapi.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${mongodb.host}")
    private String mongodbHost;

    @Value("${mongodb.port}")
    private Integer mongodbPort;

    @Value("${mongodb.database.name}")
    private String mongoDatabaseName;

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(mongodbHost, mongodbPort);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), mongoDatabaseName);
    }
}
