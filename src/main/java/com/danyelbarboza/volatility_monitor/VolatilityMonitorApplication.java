package com.danyelbarboza.volatility_monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VolatilityMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolatilityMonitorApplication.class, args);
		
	}

}
