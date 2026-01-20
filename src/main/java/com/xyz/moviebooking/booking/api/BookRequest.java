package com.xyz.moviebooking.booking.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class BookRequest {

    @NotNull
    private UUID showId;

    @NotEmpty
    private List<String> seatIds;

    @NotNull
    private Long userId;

    private String paymentMethod;

    private String couponCode;
}

