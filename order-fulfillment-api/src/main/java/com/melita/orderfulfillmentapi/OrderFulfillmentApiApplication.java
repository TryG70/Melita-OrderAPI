package com.melita.orderfulfillmentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderFulfillmentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderFulfillmentApiApplication.class, args);
    }
}
