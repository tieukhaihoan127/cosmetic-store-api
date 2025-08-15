package com.vincent.beauty_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String district;

    private String ward;

    private String address;

    private String addressNumber;

    private String phoneNumber;

    private double shippingFee;

    private String shippingMethod;

    private String taxNumber;

    private String comPhone;

    private String comName;

    private String comEmail;

    private String comAddress;

    @OneToOne(mappedBy = "billDelivery")
    private Bill bill;
}
