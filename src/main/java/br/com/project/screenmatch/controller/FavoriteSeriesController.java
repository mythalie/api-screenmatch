package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.*;
import br.com.project.screenmatch.service.FavoriteSeriesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/favoriteSeries")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class FavoriteSeriesController {

    @Autowired
    private FavoriteSeriesService service;

    @GetMapping
    public ResponseEntity<List<FavoriteSeriesResponse>> getFavoriteSeries() {
        List<FavoriteSeriesResponse> allSeries = service.getAllFavoriteSeries();
        return ResponseEntity.ok(allSeries);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid FavoriteSeriesCreateRequest createRequest) {
        ResponseBase<FavoriteSeriesResponse> createReturn = service.create(createRequest);
        return ResponseEntity.ok(createReturn);
    }

    @PutMapping("{id}")
    public ResponseEntity editTitle(@PathVariable Long id, @RequestBody @Valid FavoriteSeriesUpdateRequest updateRequest) {
        ResponseBase<FavoriteSeriesResponse> titleUpdate = service.editTitle(id, updateRequest);
        return ResponseEntity.ok(titleUpdate);
    }

    /*
    Outra maneira de restringir o acesso a determinadas funcionalidades, com base no perfil dos usuários, é com a utilização de um recurso
    do Spring Security conhecido como Method Security.
    A anotação @Secured("ROLE_ADMIN"), apenas usuários com o perfil ADMIN possam disparar requisições para deletar.
    A anotação @Secured pode ser adicionada em métodos individuais ou mesmo na classe, que seria o equivalente a adicioná-la em todos os métodos.
    Por padrão esse recurso vem desabilitado no spring Security, sendo que para o utilizar devemos adicionar a seguinte anotação na
    classe Securityconfigurations do projeto: @EnableMethodSecurity(securedEnabled = true)
     */
    @DeleteMapping("{id}")
    // @Secured("ROLE_ADMIN")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseBase<FavoriteSeriesResponse> remove = service.delete(id);
        return ResponseEntity.ok(remove);
    }
}
