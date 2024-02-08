package br.com.project.screenmatch.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public class FavoriteSeriesCreateRequest {
    @NotEmpty(message = "Título deve ser definido")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
