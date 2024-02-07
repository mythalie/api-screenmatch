package br.com.project.screenmatch.service;

import br.com.project.screenmatch.domain.dto.*;
import br.com.project.screenmatch.domain.entity.FavoriteSeries;
import br.com.project.screenmatch.domain.entity.ShareSerie;
import br.com.project.screenmatch.repository.FavoriteSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteSeriesService {

    @Autowired
    private FavoriteSeriesRepository repository;

    public List<FavoriteSeriesResponse> getAllFavoriteSeries() {
        return convertData(repository.findAll());
    }

    public ResponseBase<FavoriteSeriesResponse> create(FavoriteSeriesCreateRequest createRequest) {
        FavoriteSeries favoriteSeries = new FavoriteSeries();
        favoriteSeries.setTitle(createRequest.getTitle());

        FavoriteSeries favoriteSeriesSave = repository.save(favoriteSeries);
        return new ResponseBase<>(new FavoriteSeriesResponse(favoriteSeriesSave));
    }

    public ResponseBase<FavoriteSeriesResponse> editTitle(Long id, FavoriteSeriesUpdateRequest updateRequest) {
        FavoriteSeries favoriteSeries = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found"));

        favoriteSeries.setTitle(updateRequest.getTitle());
        FavoriteSeries updatedFavoriteSeries = repository.save(favoriteSeries);
        return new ResponseBase<>(new FavoriteSeriesResponse(updatedFavoriteSeries));
    }

    public ResponseBase<FavoriteSeriesResponse> delete(Long id) {
        FavoriteSeries favoriteSeries = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found"));

        repository.delete(favoriteSeries);
        return new ResponseBase<>(new FavoriteSeriesResponse(favoriteSeries));
    }

    private List<FavoriteSeriesResponse> convertData(List<FavoriteSeries> favoriteSeries) {
        return favoriteSeries.stream()
                .map(this::convertToFavoriteSeriesResponse)
                .collect(Collectors.toList());
    }

    private FavoriteSeriesResponse convertToFavoriteSeriesResponse(FavoriteSeries favoriteSeries) {
        return new FavoriteSeriesResponse(favoriteSeries);
    }
}
