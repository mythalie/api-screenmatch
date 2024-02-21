package br.com.project.screenmatch.domain.dto;

import br.com.project.screenmatch.repository.UserRepository;
import br.com.project.screenmatch.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Recupera o token JWT do cabeçalho de autorização da requisição através do método recoverToken
        var tokenJWT = recoverToken(request);

        if (tokenJWT != null) {
            // Utiliza o TokenService para obter o assunto do token, que normalmente representa o nome de usuário.
            var subject = tokenService.getSubject(tokenJWT);

            // Busca no repositório de usuários pelo usuário correspondente ao assunto do token.
            var user = repository.findByLogin(subject);

            /*
            Com o usuário recuperado, cria uma instância de UsernamePasswordAuthenticationToken, que é uma implementação de Authentication do Spring Security,
            representando a autenticação do usuário.
             */
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            /*
            Por fim, define essa autenticação no contexto de segurança usando SecurityContextHolder,
            permitindo que o Spring Security tenha conhecimento sobre o usuário autenticado durante a execução da requisição.
             */
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Permite que a requisição prossiga seu fluxo normal.
        filterChain.doFilter(request, response);
    }

    /*
    Este método auxiliar recupera o token JWT do cabeçalho de autorização da requisição HTTP.
    Ele verifica se o cabeçalho de autorização não é nulo e, se estiver presente, remove o prefixo "Bearer " do valor do cabeçalho, retornando apenas o token JWT.
     */
    private String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
