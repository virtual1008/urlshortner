package com.Project.UrlShortner.config;

import com.Project.UrlShortner.id.IdGenerator;
import com.Project.UrlShortner.id.SnowflakeIdGenerator;
import com.Project.UrlShortner.id.ZookeeperIdGenerator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdGeneratorConfig {

    @Value("${app.id.generator.type}")
    private String type;

    @Value("${app.snowflake.node-id}")
    private long nodeId;

    @Bean
    public IdGenerator idGenerator() {

        if ("snowflake".equalsIgnoreCase(type)) {
            return new SnowflakeIdGenerator(nodeId);
        }

        if ("zookeeper".equalsIgnoreCase(type)) {
            return new ZookeeperIdGenerator();
        }

        throw new IllegalArgumentException("Invalid ID generator type");
    }
}
