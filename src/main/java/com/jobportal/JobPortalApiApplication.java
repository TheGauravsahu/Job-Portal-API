package com.jobportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobPortalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalApiApplication.class, args);
	}

}
