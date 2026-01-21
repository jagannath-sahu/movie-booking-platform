package com.xyz.moviebooking.booking.service;

import com.xyz.moviebooking.booking.domain.BookingContext;
import com.xyz.moviebooking.offers.strategy.DiscountStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountEngine {

    private final List<DiscountStrategy> strategies;

    public DiscountEngine(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    public BigDecimal applyDiscounts(BookingContext ctx) {
        BigDecimal price = ctx.getBasePrice();
        for (DiscountStrategy strategy : strategies) {
            if (strategy.isApplicable(ctx)) {
                price = strategy.apply(ctx, price);
            }
        }
        return price;
    }
}

