package com.melita.mailservice.details;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Builder
@Data
public class OrderDetails implements Serializable {


    private Long id;
    private String customerName;
    private String customerEmail;
    private String installationAddress;
    private String installationDates;
    private String product;
    private String productPackage;
    private Boolean isApproved;
}
