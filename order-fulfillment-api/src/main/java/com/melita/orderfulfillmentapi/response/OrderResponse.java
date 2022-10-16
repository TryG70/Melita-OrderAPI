package com.melita.orderfulfillmentapi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Builder
@Data
@Getter
@Setter
public class OrderResponse implements Serializable {

    private String customerName;
    private String customerEmail;
    private String installationAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH")
    private LocalDateTime installationDates;
    private String product;
    private String productPackage;
}
