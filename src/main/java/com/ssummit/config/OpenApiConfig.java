package com.ssummit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI SaveSummitProject () {
		return new OpenAPI()
				.info(new Info()
						.description("Сайт для организации походов с оповещениями безопасности")
						.title("Походник")
						.version("0.1 alfa")
						.contact(new Contact().name("Башта Евгений, Китабов Денис, Данилов Максим")));
	}
}
