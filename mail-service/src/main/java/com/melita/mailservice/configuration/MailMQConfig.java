package com.melita.mailservice.configuration;

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
public class MailMQConfig {

    public static final String ORDER_MAIL_QUEUE = "order-mail-queue";
    public static final String ORDER_MAIL_EXCHANGE = "order-mail-exchange";
    public static final String ORDER_MAIL_ROUTING_KEY = "order-mail-routing-key";

    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_MAIL_QUEUE);
    }


    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_MAIL_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue orderQueue, TopicExchange orderExchange) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderExchange)
                .with(ORDER_MAIL_ROUTING_KEY);
    }


    @Bean
    public MessageConverter messageConverter() {

        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().registerModule(new ParanamerModule());

        return new Jackson2JsonMessageConverter(mapper);


    }

    @Bean
    public AmqpTemplate ampqTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
