package com.photon.obdeservice.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photon.obdeservice.model.Vendor;
import com.photon.obdeservice.service.VendorService;
import com.photon.obdeservice.util.GenerateQrCode;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("vendor")
public class VendorController {


    private  final VendorService service;
    
    @PostMapping("/add")
    public ResponseEntity<Vendor> addVendor(@RequestBody Map<String,Object> request){
        
        Vendor vendor=new Vendor();
        vendor.setVendorName(request.get("vendorName").toString());
        vendor.setVendorCode(request.get("vendorCode").toString());
        vendor.setQrCode(GenerateQrCode.getVendorCode());
        vendor.setServiceName(request.get("serviceName").toString());
        return ResponseEntity.ok(service.addVendor(vendor));
    }

}
