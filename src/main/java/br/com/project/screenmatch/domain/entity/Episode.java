package br.com.project.screenmatch.domain.entity;

import br.com.project.screenmatch.domain.entity.Serie;
import br.com.project.screenmatch.model.EpisodeData;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer season;
    private String title;
    private Integer episodeNumber;
    private Double rating;
    private LocalDate releaseDate;

    @ManyToOne
    private Serie serie;

    public Episode(Integer seasonNumber, EpisodeData episodeData) {
        this.season = seasonNumber;
        this.title = episodeData.title();
        this.episodeNumber = episodeData.number();

        String ratingValue = episodeData.rating();
        this.rating = parseRating(ratingValue);

        String releaseDateValue = episodeData.releaseDate();
        this.releaseDate = parseReleaseDate(releaseDateValue);
    }

    @Override
    public String toString() {
        return  "Temporada: " + season + " - Episódio: " + episodeNumber + ". " +
                title + " - Avaliação: " + rating + " - Data lançamento: " + releaseDate ;
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
