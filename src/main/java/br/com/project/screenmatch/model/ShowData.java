package br.com.project.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ShowData(@JsonAlias("Title") String title, @JsonAlias("totalSeasons") Integer totalSeasons, @JsonAlias("imdbRating") String rating) {
}
