package com.photon.obdeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.photon.obdeservice.service.ApiService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ObdeserviceApplication implements CommandLineRunner{


	public static void main(String[] args) {
		
		SpringApplication.run(ObdeserviceApplication.class, args);
		
	}
	@Override
	public void run(String... args) throws Exception {
		//apiService.callExternalApi("8800773050");
	}

}
