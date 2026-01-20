package com.xyz.moviebooking.booking.service;

import com.xyz.moviebooking.booking.domain.BookingContext;
import com.xyz.moviebooking.common.events.Topics;
import com.xyz.moviebooking.inventory.service.SeatInventoryService;
import com.xyz.moviebooking.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.xyz.moviebooking.booking.api.BookRequest;
import org.springframework.transaction.annotation.Transactional;
import com.xyz.moviebooking.payment.domain.PaymentRequest;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    private final SeatInventoryService inventoryService;
    private final PaymentService paymentService;
    private final DiscountEngine discountEngine;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final BookingContextFactory bookingContextFactory;

    public BookingService(SeatInventoryService inventoryService, PaymentService paymentService, DiscountEngine discountEngine, @Qualifier("jsonKafkaTemplate") KafkaTemplate<String, Object> kafkaTemplate, BookingContextFactory bookingContextFactory) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.discountEngine = discountEngine;
        this.kafkaTemplate = kafkaTemplate;
        this.bookingContextFactory = bookingContextFactory;
    }

    @Transactional
    public UUID bookTickets(BookRequest req) {
        log.info("booking ticket");

        UUID bookingId = UUID.randomUUID();

        inventoryService.lockSeats(req.getShowId(), req.getSeatIds());

        BookingContext ctx = bookingContextFactory.create(req, bookingId);

        BigDecimal finalPrice = discountEngine.applyDiscounts(ctx);

        try {
            paymentService.pay(new PaymentRequest(bookingId, finalPrice));
        } catch (Exception e) {
            kafkaTemplate.send(Topics.PAYMENT_FAILED, bookingId);
            throw e;
        }

        kafkaTemplate.send(Topics.BOOKING_CONFIRMED, bookingId);
        log.info("booking ticket complete");
        return bookingId;
    }
}

