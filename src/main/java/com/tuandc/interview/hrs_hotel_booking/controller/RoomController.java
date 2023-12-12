package com.tuandc.interview.hrs_hotel_booking.controller;

import com.tuandc.interview.hrs_hotel_booking.entity.RoomEntity;
import com.tuandc.interview.hrs_hotel_booking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController extends BaseController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomEntity> getAllRooms() {
        return roomService.getAllRooms();
    }

    // Other endpoints for room operations (create, update, delete, etc.)
    @GetMapping("/available")
    public List<RoomEntity> getAvailableRoomsForHotel(
            @RequestParam Long hotelId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate
    ) {
        if (checkInDate == null) {
            checkInDate = LocalDate.now(); // Set default value to today if checkInDate is not provided
        }

        if (checkOutDate == null) {
            checkOutDate = LocalDate.now().plusMonths(1); // Set default value to next 2 days if checkOutDate is not provided
        }
        return roomService.getAvailableRoomsForHotel(hotelId, checkInDate, checkOutDate);
    }
}

