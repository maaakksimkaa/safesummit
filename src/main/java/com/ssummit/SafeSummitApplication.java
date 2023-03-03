package com.ssummit;

import com.ssummit.config.TourSafetySettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(TourSafetySettings.class)
public class SafeSummitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafeSummitApplication.class, args);
		log.info("Swagger-ui runs at: http://localhost:3939/swagger-ui/index.html");
	}

}
