package com.melita.orderfulfillmentapi.entity;


import lombok.*;

import javax.persistence.*;


@Data
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
    private String installationDates;
    private String product;
    private String productPackage;
    private Boolean isApproved;
}
