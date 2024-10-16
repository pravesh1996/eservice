package com.photon.obdeservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.photon.obdeservice.service.ApiService;

@RestController
@RequestMapping("customerapi")
public class ApiController {
    @Autowired
	private  ApiService apiService;

    @PostMapping
    public ResponseEntity<Map<String,Object>> callApi(@RequestParam("mobileno") String mobileno,@RequestParam("ownerid") String ownerid){
       
        if (ownerid.equals("50504")) {
            return ResponseEntity.ok(apiService.callExternalApi1(mobileno,ownerid));
        }else if(ownerid.equals("50936")){
            return ResponseEntity.ok(apiService.callExternalApi2(mobileno,ownerid));

        }else{
            ResponseEntity.ok(new HashMap<>().put("status", "no call api for owner id "+ownerid+""));
        }
        Map<String,Object> _map=new HashMap<>();
        _map.put("status", "owner id not found for api");
        return ResponseEntity.ok(_map);
    }

}
