//package com.dev.spring_kafka.config;
//
//import com.dev.spring_kafka.pojo.Farewell;
//import com.dev.spring_kafka.pojo.Greetings;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.converter.RecordMessageConverter;
//import org.springframework.kafka.support.converter.StringJsonMessageConverter;
//import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
//import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServer;
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "foo"); // what is group id config?
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//        return new DefaultKafkaConsumerFactory<>(properties);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> partitionsKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//    @Bean ConcurrentKafkaListenerContainerFactory<String, String> filterKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setRecordFilterStrategy(record -> record.value().contains("World"));
//        return factory;
//    }
//
//
//    // Consumer Greetings
//
//    @Bean
//    public ConsumerFactory<String, Greetings> greetingConsumerFactory() {
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "bar");
//        return new DefaultKafkaConsumerFactory<>(
//                properties,
//                new StringDeserializer(),
//                new JsonDeserializer<>(Greetings.class));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Greetings> greetingKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Greetings> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(greetingConsumerFactory());
//        return factory;
//    }
//
//
//    /**
//     * Configuration not working
//     *
//
//    // MultiType
//    @Bean
//    public RecordMessageConverter messageConverter() {
//
//        // Mappings Class
//        Map<String, Class<?>> mappings = new HashMap<>();
//        mappings.put("greetings", com.dev.spring_kafka.pojo.Greetings.class);
//        mappings.put("Farewell", com.dev.spring_kafka.pojo.Farewell.class);
//
//        // DefaultJacson2JavaTypeMapper
//        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
//        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
//        typeMapper.addTrustedPackages("*");
//        typeMapper.setIdClassMapping(mappings);
//
//        // StringJsonMessageConverter
//        StringJsonMessageConverter converter = new StringJsonMessageConverter();
//        converter.setTypeMapper(typeMapper);
//
//        return converter;
//    }
//
//
//    @Bean
//    public ConsumerFactory<String, Object> multiTypeConsumerFactory() {
//        HashMap<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//
//
//
//        // Mappings Class
////        Map<String, Class<?>> mappings = new HashMap<>();
////        mappings.put("greetings", com.dev.spring_kafka.pojo.Greetings.class);
////        mappings.put("Farewell", com.dev.spring_kafka.pojo.Farewell.class);
////
////        // DefaultJacson2JavaTypeMapper
////        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
////        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
////        typeMapper.addTrustedPackages( "*");
////        typeMapper.setIdClassMapping(mappings);
//
//        JsonDeserializer<Object> deserializer = new JsonDeserializer<>(Object.class);
////        deserializer.setTypeMapper(typeMapper);
//        deserializer.trustedPackages("*");
//
//
//        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Object> multiTypeKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(multiTypeConsumerFactory());
//        factory.setRecordMessageConverter(messageConverter());
//        return factory;
//    }
//
//
////    @Bean
////    public ConsumerFactory<String, Farewell> consumerFactory() {
////        JsonDeserializer<Farewell> deserializer = new JsonDeserializer<>(Farewell.class);
////        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
////
////        Map<String, Class<?>> mappings = new HashMap<>();
////        mappings.put("Farewell", Farewell.class); // Map "__TypeId__" to Farewell class
////        typeMapper.setIdClassMapping(mappings);
////        deserializer.setTypeMapper(typeMapper);
////
////        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), deserializer);
////    }
//
//    **/
//
//    @Bean
//    public RecordMessageConverter multiTypeConverter() {
//        StringJsonMessageConverter converter = new StringJsonMessageConverter();
//        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
//        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
//        typeMapper.addTrustedPackages("com.dev.spring_kafka.pojo");
//        Map<String, Class<?>> mappings = new HashMap<>();
//        mappings.put("greetings", Greetings.class);
//        mappings.put("farewell", Farewell.class);
//        typeMapper.setIdClassMapping(mappings);
//        converter.setTypeMapper(typeMapper);
//        return converter;
//    }
//
//    @Bean
//    public ConsumerFactory<String, Object> multiTypeConsumerFactory() {
//        HashMap<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Object> multiTypeKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(multiTypeConsumerFactory());
//        factory.setRecordMessageConverter(multiTypeConverter());
//        return factory;
//    }
//
//}
