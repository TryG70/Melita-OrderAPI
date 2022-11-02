package com.melita.orderapprovalapi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Builder
@Data
public class OrderResponse implements Serializable {

    private String customerName;
    private String customerEmail;
    private String installationAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH")
    private LocalDateTime installationDate;
    private String product;
    private String productPackage;
    private Boolean isApproved;
}
