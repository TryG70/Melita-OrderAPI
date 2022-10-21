package com.melita.ordertakingapi.serviceImpl;

import com.melita.ordertakingapi.request.OrderRequest;
import com.melita.ordertakingapi.response.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {


    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    private OrderRequest orderRequest;

    private LocalDateTime time;



    @BeforeEach
    void setUp() {

        time = LocalDateTime.of(2022, Month.OCTOBER, 19, 7, 0, 0, 0);

        orderRequest = OrderRequest.builder()
                .customerName("TryGod")
                .customerEmail("trygodnwakwasi@gmail.com")
                .installationAddress("House 3, 4th Avenue, Gwarinpa")
                .installationDates(time)
                .product("internet_1gbps")
                .build();


    }

    @Test
    void validCreateOrder() {

        OrderResponse orderResponse = OrderResponse.builder()
                .customerName("TryGod")
                .customerEmail("trygodnwakwasi@gmail.com")
                .installationAddress("House 3, 4th Avenue, Gwarinpa")
                .installationDates(time)
                .product("internet_1gbps")
                .productPackage("Internet 1GBps")
                .build();

        var actual = orderServiceImpl.createOrder(orderRequest);

        assertEquals(orderResponse.getCustomerName(), actual.getCustomerName());
        assertEquals(orderResponse.getCustomerEmail(), actual.getCustomerEmail());
        assertEquals(orderResponse.getInstallationAddress(), actual.getInstallationAddress());
        assertEquals(orderResponse.getInstallationDates(), actual.getInstallationDates());
        assertEquals(orderResponse.getProduct(), actual.getProduct());
        assertEquals(orderResponse.getProductPackage(), actual.getProductPackage());

    }

    @Test
    void falseCreateOrder() {

        OrderResponse orderResponse = OrderResponse.builder()
                .customerName("Amanda")
                .customerEmail("amandaayeni34.com")
                .installationAddress("House 10, 5th Avenue, Victoria Island")
                .installationDates(LocalDateTime.now())
                .product("tv_90_channels")
                .productPackage("TV with 90 Channels")
                .build();

        var actual = orderServiceImpl.createOrder(orderRequest);

        assertNotEquals(orderResponse.getCustomerName(), actual.getCustomerName());
        assertNotEquals(orderResponse.getCustomerEmail(), actual.getCustomerEmail());
        assertNotEquals(orderResponse.getInstallationAddress(), actual.getInstallationAddress());
        assertNotEquals(orderResponse.getInstallationDates(), actual.getInstallationDates());
        assertNotEquals(orderResponse.getProduct(), actual.getProduct());
        assertNotEquals(orderResponse.getProductPackage(), actual.getProductPackage());

    }
}