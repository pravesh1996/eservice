package com.photon.obdeservice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Object> callExternalApi1(String mobileNo,String owneString) {
    
    String sql = "SELECT NAME FROM users_api USE INDEX(mobile_no) WHERE mobile_no = ?";
        String name = null;
        Map<String,Object> _map=new HashMap<>();
        String insertSql = "INSERT INTO api_call_logs (url, owner_id, mobile_no, status) VALUES (?, ?, ?, ?)";

        try {
            name = jdbcTemplate.queryForObject(sql, new Object[]{mobileNo}, String.class);

            String name1 = String.format("प्रिय %s नमस्कार  !", name);
            String param1 = String.format("प्रिय %s", name);
            String param3 = "value3";
            String channel = "WHATSAPP";

            String url = "http://go2market.ai:3000/download-pdf?name=" + name1 + "&mobileno=" + mobileNo +
                    "&param1=" + param1 + "&param3=" + param3 + "&channel=" + channel;
              restTemplate.getForObject(url, String.class);
            log.info("API called successfully for mobileNo: {}. Response: {}", mobileNo, HttpStatus.OK);
            _map.put("status", "API called successfully for mobileNo: "+mobileNo+" Response: "+HttpStatus.OK+" ");
           
            jdbcTemplate.update(insertSql, url, owneString, mobileNo, _map.get("status").toString());


        } catch (EmptyResultDataAccessException e) {
            log.warn("Mobile number {} does not exist in the database.", mobileNo);
            _map.put("status", "Mobile number " + mobileNo + " does not exist in the database.");
            jdbcTemplate.update(insertSql, null, owneString, mobileNo, "Mobile number not found");

        } catch (Exception e) {
            log.error("An error occurred while processing mobileNo: {}. Error: {}", mobileNo, e.getMessage(), e);
            _map.put("status", "Error occurred for mobileNo: " + mobileNo + ". Error: " + e.getMessage());
            jdbcTemplate.update(insertSql, null, owneString, mobileNo, "Error: " + e.getMessage());


        }
        return _map;

    }



    public Map<String, Object> callExternalApi2(String mobileNo,String owneString) {
   
    
        String sql = "SELECT NAME FROM users_api USE INDEX(mobile_no) WHERE mobile_no = ?";
            String name = null;
            Map<String,Object> _map=new HashMap<>();
            String insertSql = "INSERT INTO api_call_logs (url, owner_id, mobile_no, status) VALUES (?, ?, ?, ?)";
            try {
                name = jdbcTemplate.queryForObject(sql, new Object[]{mobileNo}, String.class);
    
                String name1 = String.format("प्रिय %s नमस्कार  !", name);
                String param1 = String.format("प्रिय %s", name);
                String param3 = "value3";
                String channel = "WHATSAPP";
    
                String url = "http://go2market.ai:2999/download-pdf?name=" + name1 + "&mobileno=" + mobileNo +
                        "&param1=" + param1 + "&param3=" + param3 + "&channel=" + channel;
                 restTemplate.getForObject(url, String.class);
                log.info("API called successfully for mobileNo: {}. Response: {}", mobileNo, HttpStatus.OK);
                _map.put("status", "API called successfully for mobileNo: "+mobileNo+" Response: "+HttpStatus.OK+" ");
                jdbcTemplate.update(insertSql, url, owneString, mobileNo, _map.get("status").toString());

    
            } catch (EmptyResultDataAccessException e) {
                log.warn("Mobile number {} does not exist in the database.", mobileNo);
                _map.put("status", "Mobile number " + mobileNo + " does not exist in the database.");

                jdbcTemplate.update(insertSql, null, owneString, mobileNo, "Mobile number not found");

            } catch (Exception e) {
                log.error("An error occurred while processing mobileNo: {}. Error: {}", mobileNo, e.getMessage(), e);
                _map.put("status", "Error occurred for mobileNo: " + mobileNo + ". Error: " + e.getMessage());

                jdbcTemplate.update(insertSql, null, owneString, mobileNo, "Error: " + e.getMessage());

            }
            return _map;
        }
        
    
}