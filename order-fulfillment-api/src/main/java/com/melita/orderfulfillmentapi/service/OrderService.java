package com.melita.orderfulfillmentapi.service;


import com.melita.orderfulfillmentapi.entity.Order;
import com.melita.orderfulfillmentapi.response.OrderResponse;

public interface OrderService {

    Order fulfillOrder(OrderResponse orderResponse);
}
