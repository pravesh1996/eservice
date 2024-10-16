package com.photon.obdeservice.service;

import org.springframework.stereotype.Service;

import com.photon.obdeservice.model.QRCodeGenerator;
import com.photon.obdeservice.model.Vendor;
import com.photon.obdeservice.repository.VendorRepository;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor addVendor(Vendor vendor) {
        try {
           
          //  String qrCodeText = "http://localhost:8080/vendor/" + vendor.getVendorCode();
         // String qrCodeText = "http://192.168.87.2:3000/washroom/" + vendor.getVendorCode();
          String qrCodeText = "http://192.168.87.2:3000/";
            byte[] qrCodeImage = QRCodeGenerator.generateQRCodeImage(qrCodeText, 350, 350);
            vendor.setQrCodeImage(qrCodeImage); 
            return vendorRepository.addVendor(vendor);
        } catch (Exception e) {
            e.printStackTrace();
            return vendor;
        }
    }

    public Vendor getVendorByCode(String vendorCode) {
        
        return  vendorRepository.getVendorByCode(vendorCode);
    }
}