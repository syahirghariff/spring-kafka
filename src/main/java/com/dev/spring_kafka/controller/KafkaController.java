package com.dev.spring_kafka.controller;


import com.dev.spring_kafka.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class KafkaController {

    @Autowired
    private SendMessageService service;

    @GetMapping
    public String sendMessageTopics(@RequestParam(value = "topic") String topic,
                                  @RequestParam(value = "message") String message){

        service.sendMessage(topic, message);

        return "Success sending message. topic=" + topic + " message=" + message;

    }
}
