package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.*;
import br.com.project.screenmatch.service.FavoriteSeriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/favoriteSeries")
@RequiredArgsConstructor
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

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseBase<FavoriteSeriesResponse> remove = service.delete(id);
        return ResponseEntity.ok(remove);
    }
}
