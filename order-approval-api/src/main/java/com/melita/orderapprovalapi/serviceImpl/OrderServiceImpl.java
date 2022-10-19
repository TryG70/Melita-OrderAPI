package com.melita.orderapprovalapi.serviceImpl;

import com.melita.orderapprovalapi.entity.Order;
import com.melita.orderapprovalapi.exception.OrderNotFoundException;
import com.melita.orderapprovalapi.repository.OrderRepository;
import com.melita.orderapprovalapi.response.OrderResponse;
import com.melita.orderapprovalapi.service.OrderService;
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
    public String receiveOrder(OrderResponse orderResponse) {

        Order order = Order.builder()
                .customerName(orderResponse.getCustomerName())
                .customerEmail(orderResponse.getCustomerEmail())
                .installationAddress(orderResponse.getInstallationAddress())
                .installationDates(orderResponse.getInstallationDates())
                .product(orderResponse.getProduct())
                .productPackage(orderResponse.getProductPackage())
                .build();

        orderRepository.save(order);

        return "Order received successfully";
    }

    @Override
    public OrderResponse approveOrder(Long id) {

        Order order = findOrder(id);

        return OrderResponse.builder()
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .installationAddress(order.getInstallationAddress())
                .installationDates(order.getInstallationDates())
                .product(order.getProduct())
                .productPackage(order.getProductPackage())
                .isApproved("true")
                .build();
    }


    @Override
    public OrderResponse declineOrder(Long id) {

        Order order = findOrder(id);

        return OrderResponse.builder()
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .installationAddress(order.getInstallationAddress())
                .installationDates(order.getInstallationDates())
                .product(order.getProduct())
                .productPackage(order.getProductPackage())
                .isApproved("false")
                .build();
    }

    public Order findOrder(Long id) {

        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }


}
