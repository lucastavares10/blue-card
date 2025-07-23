package com.ltfreire.bluecard_api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.card-requests}")
    private String cardRequestsTopicName;

    @Value("${kafka.topic.partitions}")
    private String cardRequestsTopicPartitions;

    @Value("${kafka.topic.replicas}")
    private String cardRequestsTopicReplicas;

    @Bean
    public NewTopic cardRequestsTopic() {
        return TopicBuilder.name(cardRequestsTopicName)
                .partitions(Integer.parseInt(cardRequestsTopicPartitions))
                .replicas(Integer.parseInt(cardRequestsTopicReplicas))
                .build();
    }
}
