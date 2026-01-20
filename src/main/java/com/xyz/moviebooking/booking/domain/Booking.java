package com.xyz.moviebooking.booking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Setter
    @Getter
    @Column(name = "show_id", nullable = false)
    private UUID showId;

    @Column(name = "seat_count", nullable = false)
    private int seatCount;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Version
    @Column(name = "version")
    private Long version;

    protected Booking() {}

    public void markConfirmed() {
        this.status = BookingStatus.CONFIRMED;
    }

    public void markFailed() {
        this.status = BookingStatus.FAILED;
    }
}

