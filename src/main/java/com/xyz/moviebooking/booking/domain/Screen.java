package com.xyz.moviebooking.booking.domain;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;

public class Screen {

    private Long id;

    @Setter
    private String name;

    private Theatre theatre;

    private List<Show> shows;

    protected Screen() {
        // JPA only
    }

    public Screen(String name, Theatre theatre) {
        this.name = name;
        this.theatre = theatre;
    }

    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public List<Show> getShows() {
        return shows;
    }
}

