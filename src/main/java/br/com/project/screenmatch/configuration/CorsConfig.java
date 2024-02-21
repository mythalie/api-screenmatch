package br.com.project.screenmatch.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /*
     CORS é um mecanismo de segurança que permite que recursos da web sejam solicitados de um domínio diferente do domínio onde o recurso originalmente foi servido.
     Isso é útil em cenários onde você tem uma aplicação web que precisa interagir com recursos de um servidor de API em um domínio diferente.
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // Qualquer solicitação vinda do domínio configurado será permitida e os métodos HTTP listados serão permitidos para essas solicitações.
                .allowedOrigins("http://127.0.0.1:5501")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}
