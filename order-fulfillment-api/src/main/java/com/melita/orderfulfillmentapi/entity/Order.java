package com.melita.orderfulfillmentapi.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


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

    @NotNull
    private String customerName;

    @NotNull
    private String customerEmail;

    @NotNull
    private String installationAddress;

    @NotNull
    private String installationDates;

    @NotNull
    private String product;

    @NotNull
    private String productPackage;

    @NotNull
    private Boolean isApproved;
}
