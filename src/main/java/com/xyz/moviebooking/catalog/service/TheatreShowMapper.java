package com.xyz.moviebooking.catalog.service;

import com.xyz.moviebooking.catalog.domain.TheatreShowDTO;
import com.xyz.moviebooking.catalog.domain.Show;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TheatreShowMapper {

    public List<TheatreShowDTO> toTheatreShowDTOs(List<Show> shows) {

        if (shows == null || shows.isEmpty()) {
            return Collections.emptyList();
        }

        // Group shows by theatre name
        Map<String, List<Show>> showsByTheatre =
                shows.stream()
                        .collect(Collectors.groupingBy(
                                s -> s.getTheatre().getName(),
                                LinkedHashMap::new,
                                Collectors.toList()
                        ));

        // Convert each group into TheatreShowDTO
        return showsByTheatre.entrySet().stream()
                .map(entry -> {

                    String theatreName = entry.getKey();
                    List<Show> theatreShows = entry.getValue();

                    List<TheatreShowDTO.ShowDTO> showDTOs =
                            theatreShows.stream()
                                    .sorted(Comparator.comparing(Show::getStartTime))
                                    .map(this::toShowDTO)
                                    .toList();

                    return new TheatreShowDTO(theatreName, showDTOs);
                })
                .toList();
    }

    private TheatreShowDTO.ShowDTO toShowDTO(Show show) {

        return new TheatreShowDTO.ShowDTO(
                show.getId(),
                show.getStartTime(),
                show.getLanguage()
        );
    }
}

