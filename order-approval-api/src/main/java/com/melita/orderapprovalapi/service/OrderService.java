package com.melita.orderapprovalapi.service;

import com.melita.orderapprovalapi.entity.Order;
import com.melita.orderapprovalapi.response.OrderResponse;

public interface OrderService {

    String receiveOrder(OrderResponse orderResponse);

    OrderResponse approveOrder(Long id);

    OrderResponse declineOrder(Long id);

    Order findOrder(Long id);
}
