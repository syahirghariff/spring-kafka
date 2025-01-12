package com.dev.spring_kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Configuration
public class KafkaTopicConfig {


    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    private final ApplicationPropertiesConfig appProperties;

    @Autowired
    public KafkaTopicConfig (ApplicationPropertiesConfig appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);

        return new KafkaAdmin(config);
    }

//    @Bean
//    public NewTopic topic1() {
//        return new NewTopic("topic1", 1, (short) 1);
//    }

    @Bean
    public KafkaAdmin.NewTopics topics() {

        List<NewTopic> newTopics = Stream.of(appProperties.getTopics())
                .flatMap(List::stream)
                .map(topic -> TopicBuilder.name(topic.getName())
                        .replicas(topic.getReplicas())
                        .partitions(topic.getPartitions())
                        .build())
                .toList();

        return new KafkaAdmin.NewTopics(newTopics.toArray(NewTopic[]::new));
    }


}
