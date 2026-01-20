package com.xyz.moviebooking.catalog.domain;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class TheatreShowDTO {

    private String theatreName;
    private List<ShowDTO> shows;

    public TheatreShowDTO(String theatreName, List<ShowDTO> shows) {
        this.theatreName = theatreName;
        this.shows = shows;
    }

/*    public TheatreShowDTO() {}

    public TheatreShowDTO(String theatreName, List<ShowDTO> shows) {
        this.theatreName = theatreName;
        this.shows = shows;
    }*/

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

 /*   public List<ShowDTO> getShows() {
        return shows;
    }*/

    public void setShows(List<ShowDTO> shows) {
        this.shows = shows;
    }

    // Inner DTO for each show timing
    public static class ShowDTO {

        private UUID showId;
        private LocalTime startTime;
        private String language;

        public ShowDTO() {}

        public ShowDTO(UUID showId, LocalTime startTime, String language) {
            this.showId = showId;
            this.startTime = startTime;
            this.language = language;
        }

        public UUID getShowId() {
            return showId;
        }

        public void setShowId(UUID showId) {
            this.showId = showId;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }
}

