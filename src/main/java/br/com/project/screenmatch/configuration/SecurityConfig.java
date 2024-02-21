package br.com.project.screenmatch.configuration;

import br.com.project.screenmatch.domain.dto.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Indica que esta classe está habilitando a segurança web fornecida pelo Spring Security na aplicação.
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    /* A anotação @Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realizar a sua injeção de dependência
    em outras classes.*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
        Define um bean chamado securityFilterChain, que configura a cadeia de filtros de segurança.
        Essa cadeia define políticas de segurança para diferentes endpoints da aplicação, como permitir acesso público a alguns endpoints,
        exigir autenticação para outros e assim por diante.
        O método addFilterBefore adiciona o securityFilter antes do filtro padrão de autenticação por nome de usuário e senha do Spring Security.
         */
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    req.requestMatchers(HttpMethod.DELETE, "/favoriteSeries").hasRole("ADMIN");
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    //  Este bean é necessário para processar as solicitações de autenticação.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    // Este bean é responsável por criptografar as senhas antes de serem armazenadas ou comparadas com as senhas fornecidas durante o processo de autenticação.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
