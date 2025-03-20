package com.dev.spring_kafka.service;

import com.dev.spring_kafka.pojo.Farewell;
import com.dev.spring_kafka.pojo.Greetings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "multitype", id = "multiGroup", containerFactory = "multiTypeKafkaListenerContainerFactory")
@Slf4j
public class MultiTypeListener {


    @KafkaHandler
    public void handleGreetings(Greetings greetings) {
        log.info("[handleGreetings] Received message={} ", greetings);
    }

    @KafkaHandler
    public void handleFarewell(Farewell farewell) {
        log.info("[handleFarewell] Received message={} ", farewell);
    }

    @KafkaHandler
    public void unknown(String message){
        log.info("[handleString] Received message={} ", message);
    }
}
