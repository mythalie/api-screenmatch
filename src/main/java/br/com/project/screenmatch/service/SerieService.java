package br.com.project.screenmatch.service;

import br.com.project.screenmatch.domain.dto.SerieResponse;
import br.com.project.screenmatch.domain.entity.Serie;
import br.com.project.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public Page<SerieResponse> getAllSeries(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
        return repository.findAll(pageable).map(this::convertToSerieResponse);
    }

    public List<SerieResponse> getTop5Series() {
        return convertData(repository.findTop5ByOrderByRatingDesc());
    }

    public Optional<SerieResponse> getSerieById(Long id) {
        return repository.findById(id)
                .map(s -> new SerieResponse(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getGenre(), s.getActors(), s.getPoster(), s.getPlot()));
    }

    private List<SerieResponse> convertData(List<Serie> series) {
        return series.stream()
                .map(this::convertToSerieResponse)
                .collect(Collectors.toList());
    }

    private SerieResponse convertToSerieResponse(Serie serie) {
        return new SerieResponse(serie.getId(), serie.getTitle(), serie.getTotalSeasons(), serie.getRating(), serie.getGenre(), serie.getActors(), serie.getPoster(), serie.getPlot());
    }
}
