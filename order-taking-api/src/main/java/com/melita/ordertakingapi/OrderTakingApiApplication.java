package com.melita.ordertakingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class OrderTakingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderTakingApiApplication.class, args);
    }
}
