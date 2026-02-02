package com.xyz.moviebooking.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatReleaseRequest {

    private UUID showId;
    private List<String> seatIds;
}
