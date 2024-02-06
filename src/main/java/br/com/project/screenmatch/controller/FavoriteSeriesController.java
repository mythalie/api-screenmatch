package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.FavoriteSeriesCreateRequest;
import br.com.project.screenmatch.domain.dto.FavoriteSeriesResponse;
import br.com.project.screenmatch.domain.dto.ResponseBase;
import br.com.project.screenmatch.service.FavoriteSeriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/favoriteSeries")
@RequiredArgsConstructor
public class FavoriteSeriesController {

    @Autowired
    private FavoriteSeriesService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid FavoriteSeriesCreateRequest createRequest) {
        ResponseBase<FavoriteSeriesResponse> createReturn = service.create(createRequest);
        return ResponseEntity.ok(createReturn);
    }
}
