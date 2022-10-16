package com.melita.orderfulfillmentapi.service;

import com.melita.orderfulfillmentapi.entity.Order;

public interface OrderService {

    void fulfillOrder(Order order);
}
