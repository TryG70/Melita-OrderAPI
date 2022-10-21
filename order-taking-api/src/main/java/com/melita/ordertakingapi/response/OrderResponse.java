package com.melita.ordertakingapi.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class OrderResponse {

    private String customerName;
    private String customerEmail;
    private String installationAddress;
    private LocalDateTime installationDate;
    private String product;
    private String productPackage;
}
