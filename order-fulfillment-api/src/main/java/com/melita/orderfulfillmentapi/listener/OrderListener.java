package com.melita.orderfulfillmentapi.listener;

import com.melita.orderfulfillmentapi.configuration.ApprovalMQConfig;
import com.melita.orderfulfillmentapi.configuration.MailMQConfig;
import com.melita.orderfulfillmentapi.entity.Order;
import com.melita.orderfulfillmentapi.exception.OrderNotApprovedException;
import com.melita.orderfulfillmentapi.response.OrderResponse;
import com.melita.orderfulfillmentapi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class OrderListener {

    private final OrderService orderService;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderListener(OrderService orderService, RabbitTemplate rabbitTemplate) {
        this.orderService = orderService;
        this.rabbitTemplate = rabbitTemplate;
    }


    @RabbitListener(queues = ApprovalMQConfig.ORDER_APPROVAL_QUEUE)
    public void listen(OrderResponse orderResponse) {

        log.info("Received message from approval queue: {}", orderResponse);



        if (orderResponse.getIsApproved().equals("true")) {

            Order order = orderService.fulfillOrder(orderResponse);

            rabbitTemplate.convertAndSend(MailMQConfig.ORDER_MAIL_EXCHANGE, MailMQConfig.ORDER_MAIL_ROUTING_KEY, order);
        } else {
            throw new OrderNotApprovedException("Order not approved");
        }

    }
}
