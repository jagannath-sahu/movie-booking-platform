package com.xyz.moviebooking.payment.client;

import com.xyz.moviebooking.payment.domain.PaymentRequest;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PaymentGatewayClient {

    private final Random random = new Random();

    /**
     * Simulates an external payment gateway.
     * Randomly fails to demonstrate circuit breaker + saga rollback.
     */
    public void pay(PaymentRequest request) {

        // simulate latency
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // simulate random failure (20% failure rate)
        if (random.nextInt(10) < 2) {
            throw new RuntimeException("Payment gateway timeout");
        }

        // success log
        System.out.println("Payment successful for booking: "
                + request.getBookingId()
                + " Amount: " + request.getAmount());
    }
}

