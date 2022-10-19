package com.melita.orderapprovalapi.serviceImpl;

import com.melita.orderapprovalapi.entity.Order;
import com.melita.orderapprovalapi.repository.OrderRepository;
import com.melita.orderapprovalapi.response.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    private Order order;

    private OrderResponse orderResponse;

    private LocalDateTime time;

    @BeforeEach
    void setUp() {

        time = LocalDateTime.of(2022, Month.OCTOBER, 19, 7, 0, 0, 0);

        order = Order.builder()
                .id(1L)
                .customerName("TryGod")
                .customerEmail("trygodnwakwasi@gmail.com")
                .installationAddress("House 3, 4th Avenue, Gwarinpa")
                .installationDates(time)
                .product("internet_1gbps")
                .productPackage("Internet 1GBps")
                .isApproved(false)
                .build();


        when(orderRepository.findById(1L)).thenReturn(Optional.of(order)).getMock();

    }

    @Test
    void receiveOrder() {

        orderResponse = OrderResponse.builder()
                .customerName("TryGod")
                .customerEmail("trygodnwakwasi@gmail.com")
                .installationAddress("House 3, 4th Avenue, Gwarinpa")
                .installationDates(time)
                .product("internet_1gbps")
                .productPackage("Internet 1GBps")
                .isApproved(false)
                .build();

        String status = "Order received successfully";

        var actual = orderServiceImpl.receiveOrder(orderResponse);

        assertEquals(status, actual);
    }

    @Test
    void approveOrder() {

        orderResponse = OrderResponse.builder()
                .customerName("TryGod")
                .customerEmail("trygodnwakwasi@gmail.com")
                .installationAddress("House 3, 4th Avenue, Gwarinpa")
                .installationDates(time)
                .product("internet_1gbps")
                .productPackage("Internet 1GBps")
                .isApproved(true)
                .build();

        var actual = orderServiceImpl.approveOrder(1L);
        assertEquals(orderResponse.getCustomerName(), actual.getCustomerName());
        assertEquals(orderResponse.getCustomerEmail(), actual.getCustomerEmail());
        assertEquals(orderResponse.getInstallationAddress(), actual.getInstallationAddress());
        assertEquals(orderResponse.getInstallationDates(), actual.getInstallationDates());
        assertEquals(orderResponse.getProduct(), actual.getProduct());
        assertEquals(orderResponse.getProductPackage(), actual.getProductPackage());
        assertEquals(orderResponse.getIsApproved(), actual.getIsApproved());
    }

    @Test
    void declineOrder() {

        orderResponse = OrderResponse.builder()
                .customerName("TryGod")
                .customerEmail("trygodnwakwasi@gmail.com")
                .installationAddress("House 3, 4th Avenue, Gwarinpa")
                .installationDates(time)
                .product("internet_1gbps")
                .productPackage("Internet 1GBps")
                .isApproved(false)
                .build();

        var actual = orderServiceImpl.declineOrder(1L);
        assertEquals(orderResponse.getCustomerName(), actual.getCustomerName());
        assertEquals(orderResponse.getCustomerEmail(), actual.getCustomerEmail());
        assertEquals(orderResponse.getInstallationAddress(), actual.getInstallationAddress());
        assertEquals(orderResponse.getInstallationDates(), actual.getInstallationDates());
        assertEquals(orderResponse.getProduct(), actual.getProduct());
        assertEquals(orderResponse.getProductPackage(), actual.getProductPackage());
        assertEquals(orderResponse.getIsApproved(), actual.getIsApproved());
    }

    @Test
    void findOrder() {

        var actual = orderServiceImpl.findOrder(1L);
        assertEquals(order.getId(), actual.getId());
        assertEquals(order.getCustomerName(), actual.getCustomerName());
        assertEquals(order.getCustomerEmail(), actual.getCustomerEmail());
        assertEquals(order.getInstallationAddress(), actual.getInstallationAddress());
        assertEquals(order.getInstallationDates(), actual.getInstallationDates());
        assertEquals(order.getProduct(), actual.getProduct());
        assertEquals(order.getProductPackage(), actual.getProductPackage());
        assertEquals(order.getIsApproved(), actual.getIsApproved());

    }
}