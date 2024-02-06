package br.com.project.screenmatch.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

public class FavoriteSeriesCreateRequest {
    @NotEmpty(message = "Título deve ser definido")
    private String title;

    public String getTitle() {
        return title;
    }
}
