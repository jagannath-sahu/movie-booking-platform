package com.xyz.moviebooking.booking.domain;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class BookingContext {

    private final UUID bookingId;
    private final UUID showId;
    private final LocalTime showTime;
    private final int seatCount;
    private final BigDecimal basePrice;
    private final List<BigDecimal> seatPrices;

    public BookingContext(
            UUID bookingId,
            UUID showId,
            LocalTime showTime,
            List<BigDecimal> seatPrices) {

        this.bookingId = bookingId;
        this.showId = showId;
        this.showTime = showTime;
        this.seatPrices = seatPrices;
        this.seatCount = seatPrices.size();
        this.basePrice = seatPrices.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public UUID getShowId() {
        return showId;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public List<BigDecimal> getSeatPrices() {
        return seatPrices;
    }

    public BigDecimal getThirdTicketPrice() {
        return seatCount >= 3 ? seatPrices.get(2) : BigDecimal.ZERO;
    }
}

