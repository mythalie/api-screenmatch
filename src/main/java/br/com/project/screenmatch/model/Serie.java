package br.com.project.screenmatch.model;

import br.com.project.screenmatch.service.QueryChatGPT;

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
        this.plot = QueryChatGPT.getTranslation(showData.plot().trim());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Category getGenre() {
        return genre;
    }

    public void setGenre(Category genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return  "title='" + title + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", rating=" + rating +
                ", genre=" + genre +
                ", actors='" + actors + '\'' +
                ", poster='" + poster + '\'' +
                ", plot='" + plot ;
    }
}
