package com.xyz.moviebooking.catalog.service;

import com.xyz.moviebooking.catalog.domain.Show;
import com.xyz.moviebooking.catalog.repo.ShowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieBrowseService {
    public final ShowRepository showRepository;

    public MovieBrowseService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> getShows(Long movieId, Long cityId, LocalDate date) {
        return showRepository.findByMovieCityAndDate(movieId, cityId, date);
    }
}

