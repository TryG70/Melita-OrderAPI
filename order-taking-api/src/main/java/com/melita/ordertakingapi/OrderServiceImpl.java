package com.melita.ordertakingapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        log.info("Order request received: {}", orderRequest);
        OrderResponse orderResponse = OrderResponse.builder()
                .customerName(orderRequest.getCustomerName())
                .customerEmail(orderRequest.getCustomerEmail())
                .installationAddress(orderRequest.getInstallationAddress())
                .installationDates(orderRequest.getInstallationDates())
                .product(orderRequest.getProduct())
                .build();

        String product = orderRequest.getProduct().toLowerCase();

        switch (product) {
            case "internet_250mbps" -> orderResponse.setProductPackage("Internet 250Mbps");
            case "internet_1gbps" -> orderResponse.setProductPackage("Internet 1GBps");
            case "tv_90_channels" -> orderResponse.setProductPackage("TV with 90 Channels");
            case "tv_140_channels" -> orderResponse.setProductPackage("TV with 140 Channels");
            case "telephony_free_on_net_calls" -> orderResponse.setProductPackage("Telephony with Free On net Calls");
            case "telephony_unlimited_calls" -> orderResponse.setProductPackage("Telephony with Free Unlimited Calls");
            case "mobile_prepaid" -> orderResponse.setProductPackage("Mobile Prepaid");
            case "mobile_postpaid" -> orderResponse.setProductPackage("Mobile Postpaid");
            default -> orderResponse.setProductPackage("Invalid Product");
        }

        //todo: invalid product exception
        if (orderResponse.getProductPackage().equals("Invalid Product")) {
            throw new IllegalArgumentException("Invalid Product");
        }

        return orderResponse;

    }

}
