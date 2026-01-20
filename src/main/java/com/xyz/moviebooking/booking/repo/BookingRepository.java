package com.xyz.moviebooking.booking.repo;

import com.xyz.moviebooking.booking.domain.Booking;
import com.xyz.moviebooking.booking.domain.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByShowId(UUID showId);

    List<Booking> findByStatus(BookingStatus status);
}

