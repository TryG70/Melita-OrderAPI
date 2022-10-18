package com.melita.orderfulfillmentapi.listener;

import com.melita.orderfulfillmentapi.configuration.MQConfig;
import com.melita.orderfulfillmentapi.entity.Order;
import com.melita.orderfulfillmentapi.exception.OrderNotApprovedException;
import com.melita.orderfulfillmentapi.response.OrderResponse;
import com.melita.orderfulfillmentapi.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderListener {

    private final OrderService orderService;

    @Autowired
    public OrderListener(OrderService orderService) {
        this.orderService = orderService;
    }


    @RabbitListener(queues = MQConfig.ORDER_APPROVAL_QUEUE)
    public void listen(OrderResponse orderResponse) {

        if (orderResponse.getIsApproved().equals("true")) {

            Order order = Order.builder()
                    .customerName(orderResponse.getCustomerName())
                    .customerEmail(orderResponse.getCustomerEmail())
                    .installationAddress(orderResponse.getInstallationAddress())
                    .installationDates(orderResponse.getInstallationDates())
                    .product(orderResponse.getProduct())
                    .productPackage(orderResponse.getProductPackage())
                    .isApproved(orderResponse.getIsApproved())
                    .build();

            orderService.fulfillOrder(order);

        } else {
            throw new OrderNotApprovedException("Order not approved");
        }



//        String email = orderResponse.getCustomerEmail();
//        String mailSubject = "Order Details";
//        String emailBody = "Kindly find the details of the Customer Order"  + "\n"
//                + "Customer Name: " + orderResponse.getCustomerName() + "\n"
//                + "Customer Email: " + orderResponse.getCustomerEmail() + "\n"
//                + "Customer Installation Address: " + orderResponse.getInstallationAddress() + "\n"
//                + "Customer Installation Date: " + orderResponse.getInstallationDates() + "\n"
//                + "Customer Installation Date: " + orderResponse.getProduct() + "\n"
//                + "Customer Installation Date: " + orderResponse.getProductPackage() + "\n";
//
//        mailService.sendEmail(email, mailSubject, emailBody);
    }
}
