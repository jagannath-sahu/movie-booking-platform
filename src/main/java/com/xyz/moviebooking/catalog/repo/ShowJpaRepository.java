package com.xyz.moviebooking.catalog.repo;

import com.xyz.moviebooking.catalog.domain.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShowJpaRepository extends JpaRepository<Show, UUID>, ShowRepository {

    @Override
    @Query("SELECT s.startTime FROM Show s WHERE s.id = :showId")
    Optional<LocalTime> findStartTimeById(@Param("showId") UUID showId);

    @Override
    @Query("""
           SELECT s FROM Show s
           JOIN s.theatre t
           WHERE s.movieId = :movieId
             AND t.cityId = :cityId
             AND s.showDate = :date
           ORDER BY t.name, s.startTime
           """)
    List<Show> findByMovieCityAndDate(@Param("movieId") Long movieId,
                                      @Param("cityId") Long cityId,
                                      @Param("date") LocalDate date);
}


