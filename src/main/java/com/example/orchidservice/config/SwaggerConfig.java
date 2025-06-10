package com.example.orchidservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI orchidServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Orchid Service API")
                        .description("REST API documentation for Orchid Service")
                        .version("1.0"));
    }
}