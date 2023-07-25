package com.retooling.egg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(servers = {@Server(url = "http://localhost:8011/ms-egg")})
public class EggMgmtApplication {

	private static final Logger logger = LoggerFactory.getLogger(EggMgmtApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EggMgmtApplication.class, args);
		logger.info("Iniciando EggMgmtApplication...");
	}

}
