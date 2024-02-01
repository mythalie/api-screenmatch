package br.com.project.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ShowData(@JsonAlias("Title") String title, @JsonAlias("totalSeasons") Integer totalSeasons, @JsonAlias("imdbRating") String rating) {

    @Override
    public String toString() {
        return "Série: " + title + " - Total de temporadas: " + totalSeasons + " - Avaliação: " + rating;
    }
}
