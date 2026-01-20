package com.xyz.moviebooking.common.config;

import com.xyz.moviebooking.common.events.Topics;
import com.xyz.moviebooking.inventory.service.SeatInventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentFailureListener {

    private final SeatInventoryService inventoryService;

    public PaymentFailureListener(SeatInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(
            topics = Topics.PAYMENT_FAILED,
            containerFactory = "jsonKafkaListenerContainerFactory"
    )
    public void handle(UUID bookingId) {
        inventoryService.releaseSeats(bookingId);
    }
}

