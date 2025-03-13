package com.dev.spring_kafka.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class SendMessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String msg){
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, msg);
        future.whenComplete((result, ex)-> {
            if(ex != null){
                log.error("Error when sending message: {}", ex.getMessage() );
                return;
            }
            log.info("Response received:{}", result);
        });
    }

}
