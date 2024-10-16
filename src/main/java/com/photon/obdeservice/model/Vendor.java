package com.photon.obdeservice.model;

import lombok.Data;

@Data
public class Vendor {
    private String vendorName;
    private String vendorCode;
    private String qrCode;
    private String serviceName;
    private byte[] qrCodeImage;

}
