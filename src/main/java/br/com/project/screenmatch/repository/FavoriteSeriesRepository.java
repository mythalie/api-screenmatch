package br.com.project.screenmatch.repository;

import br.com.project.screenmatch.domain.entity.FavoriteSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteSeriesRepository extends JpaRepository<FavoriteSeries, Long> {
}
