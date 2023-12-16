package com.example.redbus.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationConfig {
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	private Info info() {
		return new Info().title("RedBus").version("v1.0").description(
				"RedBus built with `RESTful API` using Spring Boot, Performs basic"
				+ " **CRUD** operations and field validation to the DTOs"
				);
	}

	@Bean
	OpenAPI api() {
		return new OpenAPI().info(info());
	}
}
