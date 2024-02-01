package br.com.project.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer season;
    private String title;
    private Integer episodeNumber;
    private Double rating;
    private LocalDate releaseDate;

    public Episode(Integer seasonNumber, EpisodeData episodeData) {
        this.season = seasonNumber;
        this.title = episodeData.title();
        this.episodeNumber = episodeData.number();

        String ratingValue = episodeData.rating();
        this.rating = parseRating(ratingValue);

        String releaseDateValue = episodeData.releaseDate();
        this.releaseDate = parseReleaseDate(releaseDateValue);
    }

    public Integer getSeason() {
        return season;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return  "Temporada: " + season + " - Episódio: " + episodeNumber + ". " +
                title + " - Avaliação: " + rating + " - Data lançamento: " + releaseDate ;
    }

    public String getFormattedString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Temporada: " + season + " - Episódio: " + episodeNumber + ". " +
                title + " - Avaliação: " + rating + " - Data lançamento: " +
                ((releaseDate != null) ? releaseDate.format(formatter) : "Data não disponível");
    }

    private Double parseRating(String ratingValue) {
        try {
            return Double.valueOf(ratingValue);
        } catch (NumberFormatException ex) {
            return 0.0;
        }
    }

    private LocalDate parseReleaseDate(String releaseDateValue) {
        try {
            return LocalDate.parse(releaseDateValue);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }
}
