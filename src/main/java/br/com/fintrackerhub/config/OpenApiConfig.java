package br.com.fintrackerhub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FinTrackerHub API")
                        .version("1.0.0")
                        .description("Financial tracking system with hybrid regime management")
                        .contact(new Contact()
                                .name("FinTrackerHub Team")
                        )
                );

    }
}
