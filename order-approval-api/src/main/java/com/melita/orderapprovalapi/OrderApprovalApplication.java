package com.melita.orderapprovalapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderApprovalApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrderApprovalApplication.class, args);
    }
}
