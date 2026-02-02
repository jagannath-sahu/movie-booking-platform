package com.xyz.moviebooking.booking.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ShowSeatInventory {

    private UUID id;

    @Getter
    private Show show;

    @Getter
    private String seatNumber;

    @Getter
    private SeatType seatType;

    private BigDecimal price;

    private SeatStatus status;

    private Long version;

    private LocalDateTime lockExpiry;

    protected ShowSeatInventory() {}

    public void lock() {
        this.status = SeatStatus.LOCKED;
    }

    public void release() {
        this.status = SeatStatus.AVAILABLE;
    }

    public void book() {
        this.status = SeatStatus.BOOKED;
    }
}
