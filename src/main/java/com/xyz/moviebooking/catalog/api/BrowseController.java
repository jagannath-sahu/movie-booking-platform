package com.xyz.moviebooking.catalog.api;

import com.xyz.moviebooking.catalog.domain.Show;
import com.xyz.moviebooking.catalog.domain.TheatreShowDTO;
import com.xyz.moviebooking.catalog.service.MovieBrowseService;
import com.xyz.moviebooking.catalog.service.TheatreShowMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/browse")
//@Tag(name = "Browse API")
public class BrowseController {

    public final MovieBrowseService movieBrowseService;
    private final TheatreShowMapper theatreShowMapper;

    public BrowseController(MovieBrowseService movieBrowseService, TheatreShowMapper theatreShowMapper) {
        this.movieBrowseService = movieBrowseService;
        this.theatreShowMapper = theatreShowMapper;
    }

    @GetMapping("/movies/{movieId}/cities/{cityId}")
    //@Operation(summary = "Browse theatres and shows by movie & city")
    public List<TheatreShowDTO> browse(@PathVariable Long movieId, @PathVariable Long cityId, @RequestParam LocalDate date) {
        List<Show> shows = movieBrowseService.getShows(movieId, cityId, date);
        return theatreShowMapper.toTheatreShowDTOs(shows);
    }
}

