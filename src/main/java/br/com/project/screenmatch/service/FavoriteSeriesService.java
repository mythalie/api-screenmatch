package br.com.project.screenmatch.service;

import br.com.project.screenmatch.domain.dto.FavoriteSeriesCreateRequest;
import br.com.project.screenmatch.domain.dto.FavoriteSeriesResponse;
import br.com.project.screenmatch.domain.dto.ResponseBase;
import br.com.project.screenmatch.domain.entity.FavoriteSeries;
import br.com.project.screenmatch.repository.FavoriteSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteSeriesService {

    @Autowired
    private FavoriteSeriesRepository repository;

    public ResponseBase<FavoriteSeriesResponse> create(FavoriteSeriesCreateRequest createRequest) {
        FavoriteSeries favoriteSeries = new FavoriteSeries();
        favoriteSeries.setTitle(createRequest.getTitle());

        FavoriteSeries favoriteSeriesSave = repository.save(favoriteSeries);
        return new ResponseBase<>(new FavoriteSeriesResponse(favoriteSeriesSave));
    }
}
