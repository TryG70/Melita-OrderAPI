package com.melita.orderfulfillmentapi.serviceImpl;

import com.melita.orderfulfillmentapi.repository.OrderRepository;
import com.melita.orderfulfillmentapi.response.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderServiceImpl;



    @BeforeEach
    void setUp() {


    }

    @Test
    void fulfillOrder() {

        OrderResponse orderResponse = OrderResponse.builder()
                .customerName("TryGod")
                .customerEmail("trygodnwakwasi@gmail.com")
                .installationAddress("House 3, 4th Avenue, Gwarinpa")
                .installationDates("19/10/2022 07")
                .product("internet_1gbps")
                .productPackage("Internet 1GBps")
                .isApproved(true)
                .build();

        var actual = orderServiceImpl.fulfillOrder(orderResponse);

        assertEquals(orderResponse.getCustomerName(), actual.getCustomerName());
        assertEquals(orderResponse.getCustomerEmail(), actual.getCustomerEmail());
        assertEquals(orderResponse.getInstallationAddress(), actual.getInstallationAddress());
        assertEquals(orderResponse.getInstallationDates(), actual.getInstallationDates());
        assertEquals(orderResponse.getProduct(), actual.getProduct());
        assertEquals(orderResponse.getProductPackage(), actual.getProductPackage());
        assertEquals(orderResponse.getIsApproved(), actual.getIsApproved());
    }
}