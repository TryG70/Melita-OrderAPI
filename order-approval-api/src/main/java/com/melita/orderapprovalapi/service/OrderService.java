package com.melita.orderapprovalapi.service;

import com.melita.orderapprovalapi.entity.Order;
import com.melita.orderapprovalapi.response.OrderResponse;

public interface OrderService {

    void receiveOrder(Order order);

    OrderResponse approveOrder(Long id);

    OrderResponse declineOrder(Long id);

    Order findOrder(Long id);
}
