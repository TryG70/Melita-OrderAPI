package com.melita.orderfulfillmentapi.listener;

import com.melita.orderfulfillmentapi.configuration.MQConfig;
import com.melita.orderfulfillmentapi.entity.Order;
import com.melita.orderfulfillmentapi.response.OrderResponse;
import com.melita.orderfulfillmentapi.service.OrderService;
import com.melita.orderfulfillmentapi.serviceImpl.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class OrderListener {

    private final OrderService orderService;
    private final MailService mailService;

    @Autowired
    public OrderListener(OrderService orderService, MailService mailService) {
        this.orderService = orderService;
        this.mailService = mailService;
    }


    @RabbitListener(queues = MQConfig.ORDER_QUEUE)
    public void listen(OrderResponse orderResponse) throws MessagingException {

        Order order = Order.builder()
                .customerName(orderResponse.getCustomerName())
                .customerEmail(orderResponse.getCustomerEmail())
                .installationAddress(orderResponse.getInstallationAddress())
                .installationDates(orderResponse.getInstallationDates())
                .product(orderResponse.getProduct())
                .productPackage(orderResponse.getProductPackage())
                .build();

        orderService.fulfillOrder(order);

        String email = orderResponse.getCustomerEmail();
        String mailSubject = "Order Details";
        String emailBody = "Kindly find the details of the Customer Order"  + "\n"
                + "Customer Name: " + orderResponse.getCustomerName() + "\n"
                + "Customer Email: " + orderResponse.getCustomerEmail() + "\n"
                + "Customer Installation Address: " + orderResponse.getInstallationAddress() + "\n"
                + "Customer Installation Date: " + orderResponse.getInstallationDates() + "\n"
                + "Customer Installation Date: " + orderResponse.getProduct() + "\n"
                + "Customer Installation Date: " + orderResponse.getProductPackage() + "\n";

        mailService.sendEmail(email, mailSubject, emailBody);
    }
}
