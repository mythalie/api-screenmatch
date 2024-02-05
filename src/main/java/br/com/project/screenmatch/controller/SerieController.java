package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.SerieDTO;
import br.com.project.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> getSeries() {
    return service.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> getTop5Series() {
        return service.getTop5Series();
    }
}
