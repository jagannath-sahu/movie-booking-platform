package com.xyz.moviebooking.booking.service;

import com.xyz.moviebooking.booking.api.BookRequest;
import com.xyz.moviebooking.booking.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Component
public class BookingContextFactory {

    private static final Logger log = LoggerFactory.getLogger(BookingContextFactory.class);
    private final InventoryGatewayService inventoryGatewayService;
    public BookingContextFactory(InventoryGatewayService inventoryGatewayService) {
        this.inventoryGatewayService = inventoryGatewayService;
    }

    /**
     * Builds BookingContext from request + DB state.
     * This is the single place where pricing & show metadata is assembled.
     */
    public BookingContext create(BookRequest req, UUID bookingId) {
        // Fetch locked seats for pricing
        List<SeatInfoDTO> seats = inventoryGatewayService.findLockedSeats(req.getShowId(), req.getSeatIds());
        log.info("BookingContextFactory seats size : {}", seats.size());
        log.info("BookingContextFactory request seats SeatIds size : {}", req.getSeatIds().size());

/*        if (seats.size() != req.getSeatIds().size()) {
            throw new IllegalStateException("Some seats are no longer locked");
        }*/


        // Extract prices (can be dynamic later)
        List<BigDecimal> seatPrices = seats.stream()
                .map(SeatInfoDTO::price)
                .collect(Collectors.toList());

        // Fetch show timing (needed for afternoon discount)
        LocalTime showTime = inventoryGatewayService
                .fetchStartTime(req.getShowId()).getStartTime();

        // Build immutable context
        return new BookingContext(
                bookingId,
                req.getShowId(),
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

