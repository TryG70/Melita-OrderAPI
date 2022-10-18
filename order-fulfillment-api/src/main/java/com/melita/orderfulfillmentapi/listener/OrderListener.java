package com.melita.orderfulfillmentapi.listener;

import com.melita.orderfulfillmentapi.configuration.ApprovalMQConfig;
import com.melita.orderfulfillmentapi.configuration.MailMQConfig;
import com.melita.orderfulfillmentapi.entity.Order;
import com.melita.orderfulfillmentapi.exception.OrderNotApprovedException;
import com.melita.orderfulfillmentapi.response.OrderResponse;
import com.melita.orderfulfillmentapi.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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

        if (orderResponse.getIsApproved().equals("true")) {

            Order order = Order.builder()
                    .customerName(orderResponse.getCustomerName())
                    .customerEmail(orderResponse.getCustomerEmail())
                    .installationAddress(orderResponse.getInstallationAddress())
                    .installationDates(orderResponse.getInstallationDates())
                    .product(orderResponse.getProduct())
                    .productPackage(orderResponse.getProductPackage())
                    .isApproved(orderResponse.getIsApproved())
                    .build();

            orderService.fulfillOrder(order);

            rabbitTemplate.convertAndSend(MailMQConfig.ORDER_MAIL_EXCHANGE, MailMQConfig.ORDER_MAIL_ROUTING_KEY, orderResponse);

        } else {
            throw new OrderNotApprovedException("Order not approved");
        }
    }
}
