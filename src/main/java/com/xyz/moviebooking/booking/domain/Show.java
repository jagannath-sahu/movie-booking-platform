package com.xyz.moviebooking.booking.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Show {

    @Getter
    private UUID id;

    private Long movieId;

    private LocalDate showDate;

    @Getter
    private LocalTime startTime;

    @Getter
    private String language;

    @Getter
    private Theatre theatre;

    private Screen screen;

}


