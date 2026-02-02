package com.xyz.moviebooking.booking.service;

import com.xyz.moviebooking.booking.client.InventoryClient;
import com.xyz.moviebooking.booking.domain.*;
import lombok.RequiredArgsConstructor;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryGatewayService {

    private final InventoryClient inventoryClient;

    @Retry(name = "inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "lockFallback")
    public SeatLockResponse lockSeats(SeatLockRequest req) {
        return inventoryClient.lockSeats(req);
    }

    @Retry(name = "inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "lockedFallback")
    public List<SeatInfoDTO> findLockedSeats(UUID showId, List<String> seatIds) {
        return inventoryClient.findLockedSeats(showId, seatIds);
    }

    @Retry(name = "inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallback")
    public ShowStartTimeResponse fetchStartTime(UUID showId) {
        return inventoryClient.getShowStartTime(showId);
    }

/*    @Retry(name = "inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "releaseFallback")
    public SeatReleaseResponse releaseSeats(SeatReleaseRequest req) {
        return inventoryClient.releaseSeats(req);
    }*/

    public SeatReleaseResponse releaseFallback(SeatReleaseRequest req, Throwable ex) {
        return new SeatReleaseResponse(0);
    }

    public ShowStartTimeResponse fallback(UUID showId, Throwable ex) {
        return new ShowStartTimeResponse(showId, null);
    }

    public SeatLockResponse lockFallback(SeatLockRequest req, Throwable ex) {
        throw new IllegalStateException("Inventory service unavailable — try later");
    }

    public List<SeatInfoDTO> lockedFallback(UUID showId, List<String> seatIds, Throwable ex) {
        return List.of(); // safe fallback
    }
}


