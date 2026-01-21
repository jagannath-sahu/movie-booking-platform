package com.xyz.moviebooking.offers.strategy;

import com.xyz.moviebooking.booking.domain.BookingContext;

import java.math.BigDecimal;

public interface DiscountStrategy {
    boolean isApplicable(BookingContext ctx);
    BigDecimal apply(BookingContext ctx, BigDecimal amount);
}

