package com.melita.mailservice.listener;

import com.melita.mailservice.configuration.MailMQConfig;
import com.melita.mailservice.details.OrderDetails;
import com.melita.mailservice.service.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class MailListener {

    private final MailService mailService;

    @Autowired
    public MailListener(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = MailMQConfig.ORDER_MAIL_QUEUE)
    public void listen(OrderDetails orderDetails) throws MessagingException {

        String email = orderDetails.getCustomerEmail();
        String mailSubject = "Order Details";
        String emailBody = "Kindly find the details of the Customer Order"  + "\n"
                + "Customer Name: " + orderDetails.getCustomerName() + "\n"
                + "Customer Email: " + orderDetails.getCustomerEmail() + "\n"
                + "Customer Installation Address: " + orderDetails.getInstallationAddress() + "\n"
                + "Customer Installation Date: " + orderDetails.getInstallationDates() + "\n"
                + "Customer Installation Date: " + orderDetails.getProduct() + "\n"
                + "Customer Installation Date: " + orderDetails.getProductPackage() + "\n";

        mailService.sendEmail(email, mailSubject, emailBody);

    }
}
