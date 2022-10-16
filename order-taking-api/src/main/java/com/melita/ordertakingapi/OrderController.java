package com.melita.ordertakingapi;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@Slf4j
@RestController
//@RequestMapping(value = "/api/v1/order", headers = {"Authorization"})
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping(value = "/create")
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        log.info("Order request received: {}", orderRequest);
        return orderService.createOrder(orderRequest);
    }
}