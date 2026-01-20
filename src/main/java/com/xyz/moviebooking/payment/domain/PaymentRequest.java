package com.xyz.moviebooking.payment.domain;

import java.math.BigDecimal;
import java.util.UUID;

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

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

