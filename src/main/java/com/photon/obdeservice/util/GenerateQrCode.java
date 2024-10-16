package com.photon.obdeservice.util;

import java.util.UUID;

public class GenerateQrCode {
    
    public static String getVendorCode(){
       
        return "V" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
    }
}
