package com.melita.orderapprovalapi.controller;

import com.melita.orderapprovalapi.configuration.ApprovalMQConfig;
import com.melita.orderapprovalapi.response.OrderResponse;
import com.melita.orderapprovalapi.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;



@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/order-approval")
public class OrderApprovalController {

    private final OrderService orderService;
    private final RabbitTemplate rabbitTemplate;


    @PostMapping(value = "/approved/{id}")
    public OrderResponse approveOrder(@PathVariable("id") Long id) {
        log.info("Approve Order with Id: {}", id);

        OrderResponse orderResponse = orderService.approveOrder(id);
        rabbitTemplate.convertAndSend(ApprovalMQConfig.ORDER_APPROVAL_EXCHANGE, ApprovalMQConfig.ORDER_APPROVAL_ROUTING_KEY, orderResponse);
//        return "Order Approved. Published to RabbitMQ";
        return orderResponse;
    }


    @PostMapping(value = "/declined/{id}")
    public OrderResponse declineOrder(@PathVariable("id") Long id) {
        log.info("Approve Order with Id: {}", id);

        OrderResponse orderResponse = orderService.declineOrder(id);
        rabbitTemplate.convertAndSend(ApprovalMQConfig.ORDER_APPROVAL_EXCHANGE, ApprovalMQConfig.ORDER_APPROVAL_ROUTING_KEY, orderResponse);
//        return "Order Cancelled. Published to RabbitMQ";
        return orderResponse;
    }
}
