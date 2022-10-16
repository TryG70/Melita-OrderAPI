package com.melita.ordertakingapi.controller;

import com.melita.ordertakingapi.configuration.MQConfig;
import com.melita.ordertakingapi.request.OrderRequest;
import com.melita.ordertakingapi.response.OrderResponse;
import com.melita.ordertakingapi.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@Slf4j
@RestController
//@RequestMapping(value = "/api/v1/order", headers = {"Authorization"})
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final RabbitTemplate rabbitTemplate;


    @PostMapping(value = "/create")
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        log.info("Order request received: {}", orderRequest);
        rabbitTemplate.convertAndSend(MQConfig.ORDER_EXCHANGE, MQConfig.ORDER_ROUTING_KEY, orderRequest);
        return orderService.createOrder(orderRequest);
    }
}