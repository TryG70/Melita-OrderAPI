package com.melita.ordertakingapi.service;

import com.melita.ordertakingapi.request.OrderRequest;
import com.melita.ordertakingapi.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
}
