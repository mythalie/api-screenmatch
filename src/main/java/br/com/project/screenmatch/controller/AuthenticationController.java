package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.AuthenticationCreateRequest;
import br.com.project.screenmatch.domain.entity.User;
import br.com.project.screenmatch.domain.dto.DataTokenJWT;
import br.com.project.screenmatch.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid AuthenticationCreateRequest createRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(createRequest.login(), createRequest.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }

}
