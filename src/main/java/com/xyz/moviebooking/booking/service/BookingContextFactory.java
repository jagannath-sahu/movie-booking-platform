package com.xyz.moviebooking.booking.service;

import com.xyz.moviebooking.booking.api.BookRequest;
import com.xyz.moviebooking.booking.domain.BookingContext;
import com.xyz.moviebooking.inventory.domain.ShowSeatInventory;
import com.xyz.moviebooking.inventory.repo.SeatInventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.xyz.moviebooking.catalog.repo.ShowRepository;

@Component
public class BookingContextFactory {

    private static final Logger log = LoggerFactory.getLogger(BookingContextFactory.class);
    private final SeatInventoryRepository seatInventoryRepository;
    private final ShowRepository showRepository; // assumed repo for show timing

    public BookingContextFactory(
            SeatInventoryRepository seatInventoryRepository, ShowRepository showRepository) {
        this.seatInventoryRepository = seatInventoryRepository;
        this.showRepository = showRepository;
    }

    /**
     * Builds BookingContext from request + DB state.
     * This is the single place where pricing & show metadata is assembled.
     */
    public BookingContext create(BookRequest request, UUID bookingId) {

        // 1️⃣ Fetch locked seats for pricing
        List<ShowSeatInventory> seats =
                seatInventoryRepository.findLockedSeatsForBooking(
                        request.getShowId(),
                        request.getSeatIds()
                );
        log.info("BookingContextFactory seats size : {}", seats.size());
        log.info("BookingContextFactory request seats SeatIds size : {}", request.getSeatIds().size());

        if (seats.size() != request.getSeatIds().size()) {
            throw new IllegalStateException("Some seats are no longer locked");
        }

        // 2️⃣ Extract prices (can be dynamic later)
        List<BigDecimal> seatPrices = seats.stream()
                .map(this::priceForSeat)
                .collect(Collectors.toList());

        // 3️⃣ Fetch show timing (needed for afternoon discount)
        LocalTime showTime = showRepository
                .findStartTimeById(request.getShowId())
                .orElseThrow(() -> new IllegalStateException("Show not found"));

        // 4️⃣ Build immutable context
        return new BookingContext(
                bookingId,
                request.getShowId(),
                showTime,
                seatPrices
        );
    }

    /**
     * Pricing rule per seat.
     * Can later move into PricingService.
     */
    private BigDecimal priceForSeat(ShowSeatInventory seat) {

        // simple demo pricing logic
        return switch (seat.getSeatType()) {
            case PREMIUM -> BigDecimal.valueOf(300);
            case REGULAR -> BigDecimal.valueOf(200);
            default -> BigDecimal.valueOf(150);
        };
    }
}

