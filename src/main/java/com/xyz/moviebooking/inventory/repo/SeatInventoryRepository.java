package com.xyz.moviebooking.inventory.repo;

import com.xyz.moviebooking.inventory.domain.ShowSeatInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.UUID;

public interface SeatInventoryRepository
        extends JpaRepository<ShowSeatInventory, Long> {

    /**
     * Locks available seats for a given show.
     * Uses DB-level row locking to prevent concurrent booking.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT s
        FROM ShowSeatInventory s
        WHERE s.show.id = :showId
          AND s.seatNumber IN :seatIds
          AND s.status = 'AVAILABLE'
    """)
    List<ShowSeatInventory> findAvailableSeatsForUpdate(
            @Param("showId") UUID showId,
            @Param("seatIds") List<String> seatIds
    );

    /**
     * Finds all locked seats associated with a booking.
     * Used for Saga rollback when payment fails.
     */
    @Query("""
        SELECT s
        FROM ShowSeatInventory s
        WHERE s.id = :bookingId
          AND s.status = 'LOCKED'
    """)
    List<ShowSeatInventory> findLockedSeatsByBooking(
            @Param("bookingId") UUID bookingId
    );

    @Query("""
    SELECT s
    FROM ShowSeatInventory s
    WHERE s.show.id = :showId
      AND s.status = 'LOCKED'
      AND s.seatNumber IN :seatIds
""")
    List<ShowSeatInventory> findLockedSeatsForBooking(
            @Param("showId") UUID showId,
            @Param("seatIds") List<String> seatIds
    );

    /**
     * Fetches all inventory for a show (for browse/seat map API later).
     */
    List<ShowSeatInventory> findByShowId(UUID showId);

}

