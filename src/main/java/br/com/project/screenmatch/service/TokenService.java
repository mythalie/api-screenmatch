package br.com.project.screenmatch.service;

import br.com.project.screenmatch.domain.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Este é o segredo utilizado para assinar e verificar os tokens JWT, garantindo que somente o servidor que conhece o segredo pode gerar e validar os tokens.
    @Value("${api.security.token.secret}")
    private String secret;

    public String createToken(User user) {
        /*
        Este método recebe um objeto User como parâmetro e gera um token JWT para esse usuário.
        O segredo é usado para criar um algoritmo de assinatura HMAC com o método Algorithm.HMAC256.
        O token é criado usando a biblioteca JWT (JSON Web Token), com o emissor definido como "API Screenmatch", o assunto definido como o nome de usuário
        do usuário fornecido e a data de expiração definida para duas horas após a geração do token.
        Se ocorrer um erro durante a geração do token, uma exceção RuntimeException será lançada, envolvendo a exceção JWTCreationException.
         */
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Screenmatch")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt.", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        /*
        Este método recebe um token JWT como parâmetro e verifica o seu assunto (subject), ou seja, o nome de usuário associado ao token.
         */
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Screenmatch")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant expirationDate() {
        /*
        Este método é um método auxiliar que retorna a data de expiração do token, configurada para duas horas após a geração do token.
         */
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
