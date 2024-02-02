package br.com.project.screenmatch.model;

import java.util.OptionalDouble;

public class Serie {
    private String title;
    private Integer totalSeasons;
    private Double rating;
    private Category genre;
    private String actors;
    private String poster;
    private String plot;

    public Serie(ShowData showData) {
        this.title = showData.title();
        this.totalSeasons = showData.totalSeasons();
        this.rating = OptionalDouble.of(Double.valueOf(showData.rating())).orElse(0);
        this.genre = Category.fromString(showData.genre().split(",")[0].trim());
        this.actors = showData.actors();
        this.poster = showData.poster();
        this.plot = showData.plot();
    }
}
