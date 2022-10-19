package com.melita.ordertakingapi.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {


    private String customerName;


    private String customerEmail;


    private String installationAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH")
    private LocalDateTime installationDates;

    @Pattern(regexp = "(INTERNET_250Mbps|INTERNET_1GBps|TV_90_Channels|TV_140_Channels|TELEPHONY_Free_On_Net_Calls|TELEPHONY_Unlimited_Calls|MOBILE_Prepaid|MOBILE_Postpaid)", message = "Invalid product")
    private String product;



}
