package com.xyz.moviebooking.catalog.domain;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "screen")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    // Optional: back-reference to shows
    @OneToMany(mappedBy = "screen", fetch = FetchType.LAZY)
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

