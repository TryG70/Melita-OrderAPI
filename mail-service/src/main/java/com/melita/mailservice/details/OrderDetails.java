package com.melita.mailservice.details;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Builder
@Data
@Getter
@Setter
public class OrderDetails implements Serializable {


    private Long id;
    private String customerName;
    private String customerEmail;
    private String installationAddress;
    private String installationDates;
    private String product;
    private String productPackage;
    private String isApproved;
}
