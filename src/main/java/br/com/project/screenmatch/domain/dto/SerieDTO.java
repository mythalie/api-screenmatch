package br.com.project.screenmatch.domain.dto;

import br.com.project.screenmatch.model.Category;

public record SerieDTO(Long id,
                       String title,
                       Integer totalSeasons,
                       Double rating,
                       Category genre,
                       String actors,
                       String poster,
                       String plot) {
}
