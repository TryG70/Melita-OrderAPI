package com.melita.orderfulfillmentapi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    @Id
    @SequenceGenerator(name = "order_id_sequence" , sequenceName = "order_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "order_id_sequence")
    private long id;

    private String customerName;
    private String customerEmail;
    private String installationAddress;
    private LocalDateTime installationDates;
    private String product;
    private String productPackage;
}
