package com.speedroller.app_v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI rollerSpeedAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Roller Speed API")
                        .description("Documentación de los servicios CRUD de la aplicación Roller Speed")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("soporte@rollerspeed.com")
                        )
                );
    }
}

