package com.ssummit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SafeSummitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafeSummitApplication.class, args);
		log.info("Swagger-ui runs at: http://localhost:9292/swagger-ui/index.html");
	}

}
