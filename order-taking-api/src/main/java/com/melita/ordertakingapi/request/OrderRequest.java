package com.melita.ordertakingapi.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "Customer name is required")
    private String customerName;

    @NotNull(message = "Customer email is required")
    private String customerEmail;

    @NotNull(message = "Installation address is required")
    private String installationAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH")
    @NotNull(message = "Installation date is required")
    private LocalDateTime installationDate;

    @Pattern(regexp = "(INTERNET_250Mbps|INTERNET_1GBps|TV_90_Channels|TV_140_Channels|TELEPHONY_Free_On_Net_Calls|TELEPHONY_Unlimited_Calls|MOBILE_Prepaid|MOBILE_Postpaid)", message = "Invalid product")
    @NotNull(message = "Product is required")
    private String product;



}
