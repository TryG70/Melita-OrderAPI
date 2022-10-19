package com.melita.orderfulfillmentapi.response;

import lombok.*;

import java.io.Serializable;


@Builder
@Data
@Getter
@Setter
public class OrderResponse implements Serializable {

    private String customerName;
    private String customerEmail;
    private String installationAddress;
    private String installationDates;
    private String product;
    private String productPackage;
    private Boolean isApproved;
}
