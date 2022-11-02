package com.melita.orderapprovalapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    private LocalDateTime installationDate;

    @NotNull
    private String product;

    @NotNull
    private String productPackage;

    private Boolean isApproved;
}
