package br.com.project.screenmatch.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SpringDocConfig {

    /*
    Esta classe configura uma instância personalizada de OpenAPI, que será utilizada pelo Springdoc OpenAPI para gerar a documentação da API.
    Além disso, ela define um esquema de segurança básico para a API, especificando que ela utiliza autenticação do tipo "bearer" com tokens JWT.
     */
    @Primary
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
