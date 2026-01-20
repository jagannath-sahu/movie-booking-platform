package com.xyz.moviebooking.inventory.domain;

import com.xyz.moviebooking.catalog.domain.Show;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "show_seat_inventory",
        uniqueConstraints = @UniqueConstraint(columnNames = {"show_id", "seat_number"})
)
public class ShowSeatInventory {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "show_id", nullable = false)  // ✅ CORRECT
    private Show show;

    @Column(name = "seat_number", nullable = false)
    @Getter
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false)
    @Getter
    private SeatType seatType;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SeatStatus status;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "lock_expiry")
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

    // getters + setters (important for Hibernate proxies)
}
