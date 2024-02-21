package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.SerieResponse;
import br.com.project.screenmatch.domain.entity.Serie;
import br.com.project.screenmatch.service.SerieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/series")
@SecurityRequirement(name = "bearer-key")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping("/{id}")
    public ResponseEntity<SerieResponse> getId(@PathVariable Long id) {
        Optional<SerieResponse> serieOptional = service.getSerieById(id);
        return serieOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<SerieResponse>> getSeries(Pageable pageable) {
        Page<SerieResponse> series = service.getAllSeries(pageable);
        return ResponseEntity.ok(series);
    }

    @PostMapping("/search")
    public ResponseEntity<SerieResponse> searchAndSaveSerie(@RequestParam String seriesName) {
        SerieResponse savedSerie = service.searchAndSaveSerie(seriesName);
        return ResponseEntity.ok(savedSerie);
    }

    @GetMapping("/top5")
    public ResponseEntity<List<SerieResponse>> getTop5Series() {
        List<SerieResponse> top5Series = service.getTop5Series();
        return ResponseEntity.ok(top5Series);
    }
}