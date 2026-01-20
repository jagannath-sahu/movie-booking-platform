package com.xyz.moviebooking.inventory.domain;

public enum SeatStatus {

    AVAILABLE,   // Seat is free and can be booked
    LOCKED,      // Temporarily locked during booking flow
    BOOKED;      // Permanently booked after payment success

    public boolean isBookable() {
        return this == AVAILABLE;
    }

    public boolean isLocked() {
        return this == LOCKED;
    }

    public boolean isFinal() {
        return this == BOOKED;
    }
}

