package com.melita.orderfulfillmentapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String customerName;
    private String customerEmail;
    private String installationAddress;
    private LocalDateTime installationDates;
    private String product;
    private String productPackage;
    private String isApproved;
}
