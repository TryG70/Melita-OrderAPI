package com.melita.orderapprovalapi.controller;

import com.melita.orderapprovalapi.configuration.ApprovalMQConfig;
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
    public String approveOrder(@PathVariable("id") Long id) {
        log.info("Approve Order with Id: {}", id);
        rabbitTemplate.convertAndSend(ApprovalMQConfig.ORDER_APPROVAL_EXCHANGE, ApprovalMQConfig.ORDER_APPROVAL_ROUTING_KEY, orderService.approveOrder(id));
        return "Order Approved. Published to RabbitMQ";
    }


    @PostMapping(value = "/declined/{id}")
    public String declineOrder(@PathVariable("id") Long id) {
        log.info("Approve Order with Id: {}", id);
        rabbitTemplate.convertAndSend(ApprovalMQConfig.ORDER_APPROVAL_EXCHANGE, ApprovalMQConfig.ORDER_APPROVAL_ROUTING_KEY, orderService.declineOrder(id));
        return "Order Cancelled. Published to RabbitMQ";
    }
}
