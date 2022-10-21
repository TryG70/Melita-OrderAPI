package com.melita.ordertakingapi.serviceImpl;

import com.melita.ordertakingapi.exception.InvalidProductException;
import com.melita.ordertakingapi.request.OrderRequest;
import com.melita.ordertakingapi.response.OrderResponse;
import com.melita.ordertakingapi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        log.info("Order request received: {}", orderRequest);
        if (orderRequest.getProduct().matches("(INTERNET_250Mbps|INTERNET_1GBps|TV_90_Channels|TV_140_Channels|TELEPHONY_Free_On_Net_Calls|TELEPHONY_Unlimited_Calls|MOBILE_Prepaid|MOBILE_Postpaid)")) {

            OrderResponse orderResponse = OrderResponse.builder()
                    .customerName(orderRequest.getCustomerName())
                    .customerEmail(orderRequest.getCustomerEmail())
                    .installationAddress(orderRequest.getInstallationAddress())
                    .installationDate(orderRequest.getInstallationDate())
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
            }

            return orderResponse;

        } else {
            throw new InvalidProductException("Invalid product");
        }

    }
}
