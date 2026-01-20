package com.xyz.moviebooking.catalog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "show")   // ✅ MUST MATCH EXACT TABLE NAME
public class Show {

    @Id
    @Column(name = "id", nullable = false)
    @Getter
    private UUID id;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @Column(name = "start_time", nullable = false)
    @Getter
    private LocalTime startTime;

    // ❌ You DO NOT have this column in DB — remove it
    @Column(name = "language", nullable = false)
    @Getter
    private String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id", nullable = false)
    @Getter
    private Theatre theatre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)   // ✅ matches DB
    private Screen screen;

    // getters & setters
}


