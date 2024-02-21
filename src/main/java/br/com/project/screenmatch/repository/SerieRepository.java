package br.com.project.screenmatch.repository;

import br.com.project.screenmatch.domain.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    List<Serie> findTop5ByOrderByRatingDesc();

    Optional<Serie> findByTitle(String title);
}
