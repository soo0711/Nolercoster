package com.Nolercoster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) // db 막아둠
@SpringBootApplication
public class NolercosterApplication {

	public static void main(String[] args) {
		SpringApplication.run(NolercosterApplication.class, args);
	}

}
