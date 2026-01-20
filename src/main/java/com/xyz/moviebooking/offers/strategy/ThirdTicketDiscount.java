package com.xyz.moviebooking.offers.strategy;

import com.xyz.moviebooking.booking.domain.BookingContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ThirdTicketDiscount implements DiscountStrategy {

    public boolean isApplicable(BookingContext ctx) {
        return ctx.getSeatCount() >= 3;
    }

    @Override
    public BigDecimal apply(BigDecimal amount) {
        return null;
    }

    public BigDecimal apply(BookingContext ctx, BigDecimal amount) {
        return amount.subtract(ctx.getThirdTicketPrice().multiply(BigDecimal.valueOf(0.5)));
    }
}

