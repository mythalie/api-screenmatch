package br.com.project.screenmatch.domain.dto;

import br.com.project.screenmatch.domain.entity.FavoriteSeries;

public class FavoriteSeriesResponse {
    private Long id;
    private String title;

    public FavoriteSeriesResponse(FavoriteSeries favoriteSeries) {
        this.id = favoriteSeries.getId();
        this.title = favoriteSeries.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
