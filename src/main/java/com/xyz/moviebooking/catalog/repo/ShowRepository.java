package com.xyz.moviebooking.catalog.repo;

import com.xyz.moviebooking.catalog.domain.Show;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShowRepository {

    Optional<LocalTime> findStartTimeById(UUID showId);

    List<Show> findByMovieCityAndDate(Long movieId, Long cityId, LocalDate date);
}

