package com.xyz.moviebooking.booking.domain;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class SeatLockResponse {

    private UUID showId;
    private List<String> lockedSeats;
    private Instant lockedAt;

    public SeatLockResponse(UUID showId, List<String> lockedSeats, Instant lockedAt) {
        this.showId = showId;
        this.lockedSeats = lockedSeats;
        this.lockedAt = lockedAt;
    }

    public UUID getShowId() {
        return showId;
    }

    public List<String> getLockedSeats() {
        return lockedSeats;
    }

    public Instant getLockedAt() {
        return lockedAt;
    }
}
