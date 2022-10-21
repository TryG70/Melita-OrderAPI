package com.melita.orderfulfillmentapi.serviceImpl;

import com.melita.orderfulfillmentapi.entity.Order;
import com.melita.orderfulfillmentapi.repository.OrderRepository;
import com.melita.orderfulfillmentapi.response.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    private Order savedOrder;

    private Order order;


    @BeforeEach
    void setUp() {


        order = Order.builder()
                .customerName("TryGod")
                .customerEmail("trygodnwakwasi@gmail.com")
                .installationAddress("House 3, 4th Avenue, Gwarinpa")
                .installationDates("19/10/2022 07")
                .product("internet_1gbps")
                .productPackage("Internet 1GBps")
                .isApproved(true)
                .build();


        savedOrder = Order.builder()
                .id(1L)
                .customerName("TryGod")
                .customerEmail("trygodnwakwasi@gmail.com")
                .installationAddress("House 3, 4th Avenue, Gwarinpa")
                .installationDates("19/10/2022 07")
                .product("internet_1gbps")
                .productPackage("Internet 1GBps")
                .isApproved(true)
                .build();


        when(orderRepository.save(order)).thenReturn(savedOrder);


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


        verify(orderRepository).save(order);
        assertEquals(savedOrder.getId(), actual.getId());
        assertEquals(savedOrder.getCustomerName(), actual.getCustomerName());
        assertEquals(savedOrder.getCustomerEmail(), actual.getCustomerEmail());
        assertEquals(savedOrder.getInstallationAddress(), actual.getInstallationAddress());
        assertEquals(savedOrder.getInstallationDates(), actual.getInstallationDates());
        assertEquals(savedOrder.getProduct(), actual.getProduct());
        assertEquals(savedOrder.getProductPackage(), actual.getProductPackage());
        assertEquals(savedOrder.getIsApproved(), actual.getIsApproved());
    }
}