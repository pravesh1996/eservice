package com.photon.obdeservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.photon.obdeservice.model.Vendor;
import com.photon.obdeservice.service.VendorService;

@RestController
public class QRCodeController {

    private final VendorService vendorService;

    public QRCodeController(VendorService vendorService) {
        this.vendorService = vendorService;
    }
    @GetMapping("/vendor/{vendorCode}/qrcode")
    public ResponseEntity<byte[]> getVendorQRCode(@PathVariable String vendorCode) {
    Vendor vendor = vendorService.getVendorByCode(vendorCode);

    if (vendor != null && vendor.getQrCodeImage() != null) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);  
        return new ResponseEntity<>(vendor.getQrCodeImage(), headers, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
}