package com.photon.obdeservice.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.photon.obdeservice.model.Vendor;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Repository
public class VendorRepository {

    private final JdbcTemplate jdbcTemplate;

    public VendorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Vendor addVendor(Vendor vendor) {
        String sql = "INSERT INTO vendors (vendor_name, vendor_code, qr_code, service_name, qr_code_image) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, vendor.getVendorName(), vendor.getVendorCode(), vendor.getQrCode(), vendor.getServiceName(),vendor.getQrCodeImage());
        return vendor;
    }

    public List<Map<String,Object>> getAllVendors() {
        return jdbcTemplate.queryForList("select * from vendor");
      
    }

    public Map<String,Object> getVendorById(Long id) {
        String sql = "SELECT * FROM vendors WHERE id = ?";
        return jdbcTemplate.queryForMap(sql,id);
      
    }

    public Vendor getVendorByCode(String vendorCode) {
        
        Map<String,Object> _map=jdbcTemplate.queryForMap("SELECT id, qr_code, vendor_name, vendor_code, LENGTH(qr_code_image) as qr_code_image FROM vendors where vendor_code=?",vendorCode);

        Vendor vendor=new Vendor();
        vendor.setQrCode(_map.get("qr_code").toString());
        vendor.setQrCodeImage(_map.get("qr_code_image").toString().getBytes(Charset.forName("UTF-8")));
        return vendor;
    }
}

