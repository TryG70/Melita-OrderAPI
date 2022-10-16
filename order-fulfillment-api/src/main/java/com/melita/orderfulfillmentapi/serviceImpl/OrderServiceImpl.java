package com.melita.orderfulfillmentapi.serviceImpl;

import com.melita.orderfulfillmentapi.entity.Order;
import com.melita.orderfulfillmentapi.repository.OrderRepository;
import com.melita.orderfulfillmentapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void fulfillOrder(Order order) {

        orderRepository.save(order);
    }
}
