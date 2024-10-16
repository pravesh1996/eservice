package com.photon.obdeservice.model;

import lombok.Data;

@Data
public class Payment {
    private Long id;
    private Long vendorId;  
    private double paymentAmount;
    private String paymentMode;
    private String paymentStatus;

 
}

