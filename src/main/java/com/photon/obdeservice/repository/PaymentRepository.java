package com.photon.obdeservice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.photon.obdeservice.model.Payment;

@Repository
public class PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addPayment(Payment payment) {
        String sql = "INSERT INTO payments (vendor_id, payment_amount, payment_mode, payment_status) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, payment.getVendorId(), payment.getPaymentAmount(), payment.getPaymentMode(), payment.getPaymentStatus());
    }

    public List<Map<String,Object>> getAllPayments() {
        return jdbcTemplate.queryForList("select * from payment");
    }

    public Map<String,Object> getPaymentById(Long id) {
        String sql = "SELECT * FROM payments WHERE id = ?";
        return jdbcTemplate.queryForMap(sql,id);
       
    }
}
