package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.SerieResponse;
import br.com.project.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping("/{id}")
    public Optional<SerieResponse> getId(@PathVariable Long id) {
        return service.getSerieById(id);
    }

    @GetMapping
    public List<SerieResponse> getSeries() {
    return service.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SerieResponse> getTop5Series() {
        return service.getTop5Series();
    }
}