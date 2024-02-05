package br.com.project.screenmatch.service;

import br.com.project.screenmatch.domain.dto.SerieDTO;
import br.com.project.screenmatch.domain.entity.Serie;
import br.com.project.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> getAllSeries() {
        return convertsData(repository.findAll());
    }

    public List<SerieDTO> getTop5Series() {
        return convertsData(repository.findTop5ByOrderByRatingDesc());
    }

    private List<SerieDTO> convertsData(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getGenre(), s.getActors(), s.getPoster(), s.getPlot()))
                .collect(Collectors.toList());
    }
}
