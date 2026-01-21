package com.xyz.moviebooking.inventory.service;

import com.xyz.moviebooking.inventory.errors.SeatUnavailableException;
import com.xyz.moviebooking.inventory.repo.SeatInventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import com.xyz.moviebooking.inventory.domain.ShowSeatInventory;

@Service
public class SeatInventoryService {
    private static final Logger log = LoggerFactory.getLogger(SeatInventoryService.class);

    public final SeatInventoryRepository seatInventoryRepository;

    public SeatInventoryService(SeatInventoryRepository seatInventoryRepository) {
        this.seatInventoryRepository = seatInventoryRepository;
    }

    @Transactional
    public void lockSeats(UUID showId, List<String> seatIds) {
        List<ShowSeatInventory> seats = seatInventoryRepository.findAvailableSeatsForUpdate(showId, seatIds);

        for (ShowSeatInventory s : seats) {
            log.info("Show ID = {}", s.getShow().getId());
            log.info("Start Time = {}", s.getShow().getStartTime());
        }

        if (seats.size() != seatIds.size()) {
            throw new SeatUnavailableException("One or more seats are already booked");
        }

        seats.forEach(ShowSeatInventory::lock);
    }

    @Transactional
    public void releaseSeats(UUID bookingId) {
        seatInventoryRepository.findLockedSeatsByBooking(bookingId).forEach(ShowSeatInventory::release);
    }
}

