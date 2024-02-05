package br.com.project.screenmatch.domain.entity;

import br.com.project.screenmatch.configuration.QueryChatGPT;
import br.com.project.screenmatch.model.Category;
import br.com.project.screenmatch.model.ShowData;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private Integer totalSeasons;

    private Double rating;

    @Enumerated(EnumType.STRING)
    private Category genre;

    private String actors;

    private String poster;

    private String plot;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episode> episodes = new ArrayList<>();

    public Serie() {}

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

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public Category getGenre() {
        return genre;
    }

    public void setEpisodes(List<Episode> episodes) {
        episodes.forEach(e -> e.setSerie(this));
        this.episodes = episodes;
    }

    public Long getId() {
        return id;
    }

    public String getActors() {
        return actors;
    }

    public String getPoster() {
        return poster;
    }

    public String getPlot() {
        return plot;
    }

    public Double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return  "Título: " + title + " - Total de temporadas: " + totalSeasons + " - Avaliação: " +
                rating + " - Gênero: " + genre + " - Atores: " + actors + " - Poster: " + poster + " - Trama: " + plot +
                " - Episódios: " + episodes;
    }
}
