package com.melita.orderapprovalapi.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApprovalMQConfig {

    public static final String ORDER_APPROVAL_QUEUE = "order-approval-queue";
    public static final String ORDER_APPROVAL_EXCHANGE = "order-approval-exchange";
    public static final String ORDER_APPROVAL_ROUTING_KEY = "order-approval-routing-key";

    @Bean
    public Queue orderApprovalQueue() {
        return new Queue(ORDER_APPROVAL_QUEUE);
    }


    @Bean
    public TopicExchange orderApprovalExchange() {
        return new TopicExchange(ORDER_APPROVAL_EXCHANGE);
    }

    @Bean
    public Binding bindingApproval(Queue orderQueue, TopicExchange orderExchange) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderExchange)
                .with(ORDER_APPROVAL_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageApprovalConverter() {

        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().registerModule(new ParanamerModule());

        return new Jackson2JsonMessageConverter(mapper);

    }

    @Bean
    public AmqpTemplate ampqApprovalTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageApprovalConverter());
        return rabbitTemplate;
    }
}
