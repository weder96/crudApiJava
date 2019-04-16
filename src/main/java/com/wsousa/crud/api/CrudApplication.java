package com.wsousa.crud.api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CrudApplication {
	
	public static void main(String[] args) {		   		
		SpringApplication.run(CrudApplication.class, args);
		
	}
}
