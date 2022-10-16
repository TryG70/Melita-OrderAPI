package com.melita.ordertakingapi.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MQConfig {

    public static final String ORDER_QUEUE = "order-queue";
    public static final String ORDER_EXCHANGE = "order-exchange";
    public static final String ORDER_ROUTING_KEY = "order-routing-key";

    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE);
    }


    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue orderQueue, TopicExchange orderExchange) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderExchange)
                .with(ORDER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {

        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        return new Jackson2JsonMessageConverter(mapper);
//        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate ampqTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
