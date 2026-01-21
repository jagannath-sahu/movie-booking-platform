package com.xyz.moviebooking.payment.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
public class PaymentRequest {

    private UUID bookingId;
    private BigDecimal amount;
    private String paymentMethod;

    public PaymentRequest() {}

    public PaymentRequest(UUID bookingId, BigDecimal amount) {
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = "CARD"; // default
    }

    public PaymentRequest(UUID bookingId, BigDecimal amount, String paymentMethod) {
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

}

