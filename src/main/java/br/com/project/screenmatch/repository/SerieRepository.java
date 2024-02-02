package br.com.project.screenmatch.repository;

import br.com.project.screenmatch.domain.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
