package com.melita.orderapprovalapi.listener;

import com.melita.orderapprovalapi.configuration.RequestMQConfig;
import com.melita.orderapprovalapi.response.OrderResponse;
import com.melita.orderapprovalapi.service.OrderService;
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


    @RabbitListener(queues = RequestMQConfig.ORDER_QUEUE)
    public void listen(OrderResponse orderResponse) {

        orderService.receiveOrder(orderResponse);

    }
}
