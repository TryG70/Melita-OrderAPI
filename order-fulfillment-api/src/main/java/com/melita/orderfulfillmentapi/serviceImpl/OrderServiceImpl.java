package com.melita.orderfulfillmentapi.serviceImpl;

import com.melita.orderfulfillmentapi.entity.Order;
import com.melita.orderfulfillmentapi.repository.OrderRepository;
import com.melita.orderfulfillmentapi.response.OrderResponse;
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
    public Order fulfillOrder(OrderResponse orderResponse) {

        Order order = Order.builder()
                .customerName(orderResponse.getCustomerName())
                .customerEmail(orderResponse.getCustomerEmail())
                .installationAddress(orderResponse.getInstallationAddress())
                .installationDate(orderResponse.getInstallationDate())
                .product(orderResponse.getProduct())
                .productPackage(orderResponse.getProductPackage())
                .isApproved(orderResponse.getIsApproved())
                .build();



        return orderRepository.save(order);

    }
}
