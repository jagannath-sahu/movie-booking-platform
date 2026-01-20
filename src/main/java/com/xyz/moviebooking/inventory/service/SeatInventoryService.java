package com.xyz.moviebooking.inventory.service;

import com.xyz.moviebooking.inventory.repo.SeatInventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import com.xyz.moviebooking.inventory.domain.ShowSeatInventory;

@Service
public class SeatInventoryService {

    public final SeatInventoryRepository seatInventoryRepository;

    public SeatInventoryService(SeatInventoryRepository seatInventoryRepository) {
        this.seatInventoryRepository = seatInventoryRepository;
    }

    @Transactional
    public void lockSeats(UUID showId, List<String> seatIds) {
        List<ShowSeatInventory> seats = seatInventoryRepository.findAvailableSeatsForUpdate(showId, seatIds);

        if (seats.size() != seatIds.size()) {
            throw new RuntimeException("Seat unavailable");
        }

        seats.forEach(ShowSeatInventory::lock);
    }

    @Transactional
    public void releaseSeats(UUID bookingId) {
        seatInventoryRepository.findLockedSeatsByBooking(bookingId).forEach(ShowSeatInventory::release);
    }
}

