//package com.dev.spring_kafka.service;
//
//
//import com.dev.spring_kafka.pojo.Greetings;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.PartitionOffset;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class ListenMessageService {
//
//
//    /**
//     * Listen by groupId, and multiple topics
//     * @param message
//     */
//    @KafkaListener(topics = {"topic1", "topic2"}, groupId = "foo")
//    public void listenGroupFoo(String message){
//        log.info("[listenGroupFoo] Received message in groupId=foo, message={} ", message);
//    }
//
//    /**
//     * Listen by topic
//     * @param message
//     */
//    @KafkaListener(topics = {"topic1", "topic2"}, containerFactory = "kafkaListenerContainerFactory")
//    public void listenMessage(String message){
//        log.info("[listenMessage] Received message={} ", message);
//    }
//
//    /**
//     * Listen by Headers.PartitionId
//     * @param message
//     * @param partition
//     */
//    @KafkaListener(topics = "topicName")
//    public void listenWithHeaders( @Payload String message,
//                                   @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
//        log.info("[listenWithHeaders] Received message={}, partition={} ", message, partition);
//    }
//
//
//    /**
//     * Listen with PartitionOffset
//     * @param message
//     * @param partition
//     */
//    @KafkaListener(
//            topicPartitions = @TopicPartition( topic = "topicName", partitionOffsets = {
//                                        @PartitionOffset(partition = "0", initialOffset = "0"),
//                                        @PartitionOffset(partition = "3", initialOffset = "0")}),
//            containerFactory = "partitionsKafkaListenerContainerFactory")
//    public void listenToPartition(
//            @Payload String message,
//            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
//        log.info("[listenToPartition] Received message={}, partition={} ", message, partition);
//    }
//
//
//    /**
//     * Listen With Filters
//     * @param message
//     */
//    @KafkaListener( topics = {"topic1", "topic2"}, containerFactory = "filterKafkaListenerContainerFactory")
//    public void listenWithFilter(String message) {
//        log.info("[listenWithFilter] Received message with filtered, message={} ", message);
//    }
//
//    @KafkaListener( topics = {"topic2", "topic1"}, containerFactory = "filterKafkaListenerContainerFactory")
//    public void listenWithFilter2(String message) {
//        log.info("[listenWithFilter2] Received message with filtered, message={} ", message);
//    }
//
//
//    // Consumer
//    @KafkaListener(topics = {"greetings"}, containerFactory = "greetingKafkaListenerContainerFactory")
//    public void greetingListener(Greetings message) {
//        log.info("[greetingListener] Received message={} ", message);
//    }
//}
