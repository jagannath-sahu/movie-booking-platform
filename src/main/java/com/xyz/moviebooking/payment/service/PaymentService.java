package com.xyz.moviebooking.payment.service;

import com.xyz.moviebooking.payment.client.PaymentGatewayClient;
import com.xyz.moviebooking.payment.domain.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentGatewayClient client;

    public PaymentService(PaymentGatewayClient client) {
        this.client = client;
    }

    //@CircuitBreaker(name = "payment", fallbackMethod = "fallback")
    public void pay(PaymentRequest request) {
        client.pay(request);
    }

    public void fallback(PaymentRequest req, Exception ex) {
        throw new RuntimeException("Payment service unavailable");
    }
}

