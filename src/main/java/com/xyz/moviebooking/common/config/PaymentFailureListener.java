/*
package com.xyz.moviebooking.common.config;

import com.xyz.moviebooking.booking.domain.Booking;
import com.xyz.moviebooking.booking.domain.SeatReleaseRequest;
import com.xyz.moviebooking.booking.repo.BookingRepository;
import com.xyz.moviebooking.booking.service.InventoryGatewayService;
import com.xyz.moviebooking.common.events.Topics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PaymentFailureListener {
    private final InventoryGatewayService inventoryGatewayService;
    private final BookingRepository bookingRepository;

    public PaymentFailureListener(InventoryGatewayService inventoryGatewayService, BookingRepository bookingRepository) {
        this.inventoryGatewayService = inventoryGatewayService;
        this.bookingRepository = bookingRepository;
    }

    @KafkaListener(topics = Topics.PAYMENT_FAILED, containerFactory = "jsonKafkaListenerContainerFactory")
    public void handle(UUID bookingId) {
        List<Booking> bookings = bookingRepository.findByShowId(bookingId);
        //SeatReleaseRequest req = loadFromBookingCacheOrDB(bookingId);
        //inventoryGatewayService.releaseSeats(req);
    }
}

*/
