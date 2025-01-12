package com.dev.spring_kafka.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix="app")
@Data
public class ApplicationPropertiesConfig {

    private List<Topic> topics;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Topic {
        private String name;
        private Integer partitions;
        private Integer replicas;
    }
}
