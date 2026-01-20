package com.xyz.moviebooking.offers.strategy;

import com.xyz.moviebooking.booking.domain.BookingContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;

@Component
public class AfternoonShowDiscount implements DiscountStrategy {

    public boolean isApplicable(BookingContext ctx) {
        return ctx.getShowTime().isAfter(LocalTime.NOON)
                && ctx.getShowTime().isBefore(LocalTime.of(17, 0));
    }

    public BigDecimal apply(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.8));
    }
}

