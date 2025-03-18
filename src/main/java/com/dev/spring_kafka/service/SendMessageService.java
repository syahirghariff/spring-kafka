package com.dev.spring_kafka.service;


import com.dev.spring_kafka.config.ApplicationPropertiesConfig;
import com.dev.spring_kafka.pojo.Greetings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableAsync
public class SendMessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ApplicationPropertiesConfig appProps;

    @Autowired
    private KafkaTemplate<String, Greetings> greetingsKafkaTemplate;

    public void sendMessage(String topic, String msg){
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, msg);
        future.whenComplete((result, ex)-> {
            if(ex != null){
                log.error("Error when sending message: {}", ex.getMessage() );
                return;
            }
            log.info("[sendMessage] Response received:{}", result);
        });
    }

    public void sendMessage(String topic, Greetings greetings){
        CompletableFuture<SendResult<String, Greetings>> future = greetingsKafkaTemplate.send(topic, greetings);
        future.whenComplete((result, ex)-> {
            if(ex != null){
                log.error("Error when sending message: {}", ex.getMessage() );
                return;
            }
            log.info("[sendMessageGreetings] Response received:{}", result);
        });
    }

    @Scheduled(fixedDelay = 300000) // 5 minutes
    @Async
    public void sendScheduledMessage() {

        List<ApplicationPropertiesConfig.Topic> topics = appProps.getTopics().stream().filter(t-> !StringUtils.equals("greetings", t.getName())).collect(Collectors.toList());
        for (ApplicationPropertiesConfig.Topic topic : topics) {
            String message = RandomStringUtils.randomAlphanumeric(20);
            this.sendMessage(topic.getName(), message);

            log.info("[FIRST] Scheduled message sent. topic={}, message={}", topic.getName(), message);
        }

    }

    @Scheduled(fixedDelay = 300000) // 5 minutes
    @Async
    public void sendScheduledMessage2() {
        List<ApplicationPropertiesConfig.Topic> topics = appProps.getTopics().stream().filter(t-> !StringUtils.equals("greetings", t.getName())).collect(Collectors.toList());
        for (ApplicationPropertiesConfig.Topic topic : topics) {
            String message = RandomStringUtils.randomAlphanumeric(20);
            this.sendMessage(topic.getName(), message);

            log.info("[SECOND] Scheduled message sent. topic={}, message={}", topic.getName(), message);
        }

    }


    @Scheduled(fixedDelay = 300000) // 5 minutes
    @Async
    public void sendScheduledGreetings() {
        String message = RandomStringUtils.randomAlphanumeric(20);
        String name = RandomStringUtils.randomAlphabetic(5);
        String topic = "greetings";
        Greetings greetings = Greetings.builder().name(name).message(message).build();
        this.sendMessage(topic, greetings);
        log.info("[FIRST_GREETINGS] Scheduled message sent. topic={}, message={}", topic, greetings);
    }

}
