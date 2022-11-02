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
                .installationDate(orderResponse.getInstallationDate())
                .product(orderResponse.getProduct())
                .productPackage(orderResponse.getProductPackage())
                .build();

        orderRepository.save(order);

        return "Order received successfully";
    }

    @Override
    public OrderResponse approveOrder(Long id) {

        return buildOrderResponse(true, id);
    }

    @Override
    public OrderResponse declineOrder(Long id) {

        return buildOrderResponse(false, id);
    }

    public Order findOrder(Long id) {

        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    public OrderResponse buildOrderResponse(boolean isApproved, Long id) {

        Order order = findOrder(id);

        return OrderResponse.builder()
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .installationAddress(order.getInstallationAddress())
                .installationDate(order.getInstallationDate())
                .product(order.getProduct())
                .productPackage(order.getProductPackage())
                .isApproved(isApproved)
                .build();
    }


}
