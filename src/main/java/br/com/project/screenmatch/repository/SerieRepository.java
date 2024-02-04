package br.com.project.screenmatch.repository;

import br.com.project.screenmatch.domain.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    List<Serie> findTop5ByOrderByRatingDesc();
}
