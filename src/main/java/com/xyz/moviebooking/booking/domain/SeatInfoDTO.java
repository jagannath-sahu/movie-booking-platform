package com.xyz.moviebooking.booking.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record SeatInfoDTO(UUID id, String seatNumber, String seatType, String status, BigDecimal price) {}
