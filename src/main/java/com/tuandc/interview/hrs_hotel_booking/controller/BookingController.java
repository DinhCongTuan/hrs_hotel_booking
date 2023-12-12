package com.tuandc.interview.hrs_hotel_booking.controller;

import com.tuandc.interview.hrs_hotel_booking.entity.BookingEntity;
import com.tuandc.interview.hrs_hotel_booking.model.BaseResponse;
import com.tuandc.interview.hrs_hotel_booking.model.Booking;
import com.tuandc.interview.hrs_hotel_booking.model.StatusCode;
import com.tuandc.interview.hrs_hotel_booking.services.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController extends BaseController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Get all bookings ordered by check-in date",
            description = "Retrieve all bookings ordered by check-in date in descending order with pagination")
    @GetMapping("/ordered-by-checkin-date")
    public Page<BookingEntity> getAllBookingsOrderedByCheckInDateDesc(Pageable pageable) {
        return bookingService.getAllBookingsOrderedByCheckInDateDesc(pageable);
    }


    @PostMapping("/create")
    @Operation(summary = "Create a booking")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Booking> createBooking(
            @RequestParam Long roomId,
            @RequestParam("guestName") String guestName,
            @RequestParam("guestPhone") String guestPhone,
            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {

        Booking booking = bookingService.createBooking(roomId, guestName, guestPhone, checkInDate, checkOutDate);
        BaseResponse<Booking> response = new BaseResponse<>(StatusCode.SUCCESS);
        response.setData(booking);
        return response;
    }

    @Operation(summary = "Search bookings by guest name",
            description = "Retrieve bookings by guest name within a date range")
    @GetMapping("/search")
    public List<BookingEntity> searchBookingsByGuestName(
            @Parameter(description = "Guest name to search", required = true)
            @RequestParam("guestName") String guestName,

            @Parameter(description = "Start date of search range", required = true)
            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,

            @Parameter(description = "End date of search range", required = true)
            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate
    ) {
        return bookingService.findBookingsByGuestName(guestName, checkInDate, checkOutDate);
    }

    // Other endpoints for booking operations (create, update, delete, etc.)
}
