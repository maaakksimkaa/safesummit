package com.ssummit.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
)
public class OpenApiConfig {
	@Bean
	public OpenAPI SaveSummitProject () {
		return new OpenAPI()
				.info(new Info()
						.description("Сайт для организации походов с оповещениями безопасности")
						.title("Safe Summit")
						.version("0.1 alfa")
						.contact(new Contact().name("Башта Евгений, Китабов Денис, Данилов Максим")));
	}
}
