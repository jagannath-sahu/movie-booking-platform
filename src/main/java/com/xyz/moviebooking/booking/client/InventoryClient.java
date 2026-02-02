package com.xyz.moviebooking.booking.client;

import com.xyz.moviebooking.booking.domain.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

// Eureka resolves name inventory-service
@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PostMapping("/inventory/lock")
    SeatLockResponse lockSeats(SeatLockRequest request);

    @GetMapping("/inventory/lockedSeats")
    List<SeatInfoDTO> findLockedSeats(@RequestParam("showId") UUID showId,
                                            @RequestParam("seatIds") List<String> seatIds);

    @GetMapping("/inventory/catalog/{showId}/start-time")
    ShowStartTimeResponse getShowStartTime(@PathVariable("showId") UUID showId);

    @PostMapping("/inventory/seats/release")
    SeatReleaseResponse releaseSeats(SeatReleaseRequest request);
}
