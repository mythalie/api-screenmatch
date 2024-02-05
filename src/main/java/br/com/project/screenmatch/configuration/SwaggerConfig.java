package br.com.project.screenmatch.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Screenmatch")
                        .description("Screenmatch - Spring Boot")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Screenmatch")
                                .email("s.mythalie@hotmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation")
                        .url(""));
    }
}
