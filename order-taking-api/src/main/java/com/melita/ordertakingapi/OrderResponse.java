package com.melita.ordertakingapi;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class OrderResponse {

    private String customerName;
    private String customerEmail;
    private String installationAddress;
    private LocalDateTime installationDates;
    private String product;
    private String productPackage;
}
