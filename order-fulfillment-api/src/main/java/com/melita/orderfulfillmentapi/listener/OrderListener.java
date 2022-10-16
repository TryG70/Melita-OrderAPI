package com.melita.orderfulfillmentapi.listener;

import com.melita.orderfulfillmentapi.configuration.MQConfig;
import com.melita.orderfulfillmentapi.entity.Order;
import com.melita.orderfulfillmentapi.response.OrderResponse;
import com.melita.orderfulfillmentapi.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final OrderService orderService;

    @Autowired
    public OrderListener(OrderService orderService) {
        this.orderService = orderService;
    }


    @RabbitListener(queues = MQConfig.ORDER_QUEUE)
    public void listen(OrderResponse orderResponse) {

        Order order = Order.builder()
                .customerName(orderResponse.getCustomerName())
                .customerEmail(orderResponse.getCustomerEmail())
                .installationAddress(orderResponse.getInstallationAddress())
                .installationDates(orderResponse.getInstallationDates())
                .product(orderResponse.getProduct())
                .productPackage(orderResponse.getProductPackage())
                .build();

        orderService.fulfillOrder(order);
    }
}
