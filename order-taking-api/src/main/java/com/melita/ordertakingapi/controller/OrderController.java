package com.melita.ordertakingapi.controller;

import com.melita.ordertakingapi.configuration.EnvironmentConfig;
import com.melita.ordertakingapi.configuration.MQConfig;
import com.melita.ordertakingapi.exception.NotAuthorizedException;
import com.melita.ordertakingapi.request.OrderRequest;
import com.melita.ordertakingapi.response.OrderResponse;
import com.melita.ordertakingapi.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Scope
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final RabbitTemplate rabbitTemplate;

    private final EnvironmentConfig environmentConfig;


    @PostMapping(value = "/create", headers = {"Authorization"})
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        log.info("header {}", header);
        log.info("env {}", environmentConfig.getAuthToken());


        if (!header.equals(environmentConfig.getAuthToken())) {
            throw new NotAuthorizedException("Invalid Authorization Token");
        }
        log.info("Order request received: {}", orderRequest);

        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        rabbitTemplate.convertAndSend(MQConfig.ORDER_EXCHANGE, MQConfig.ORDER_ROUTING_KEY, orderResponse);

        return orderResponse;
    }
}