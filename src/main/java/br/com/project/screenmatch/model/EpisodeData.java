package br.com.project.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(@JsonAlias("Title") String title,
                          @JsonAlias("Episode") Integer number,
                          @JsonAlias("imdbRating") String rating,
                          @JsonAlias("Released") String releaseDate) {

    @Override
    public String toString() {
        return  "Título: " + title + '\'' +
                ", Número: " + number +
                ", Avaliação: " + rating + '\'' +
                ", Data de lançamento: " + releaseDate + '\'' ;
    }
}
