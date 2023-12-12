package com.tuandc.interview.hrs_hotel_booking.service;

import com.tuandc.interview.hrs_hotel_booking.entity.BookingEntity;
import com.tuandc.interview.hrs_hotel_booking.entity.RoomEntity;
import com.tuandc.interview.hrs_hotel_booking.exception.BadParameterException;
import com.tuandc.interview.hrs_hotel_booking.exception.NotFoundEntityException;
import com.tuandc.interview.hrs_hotel_booking.model.Booking;
import com.tuandc.interview.hrs_hotel_booking.model.Status;
import com.tuandc.interview.hrs_hotel_booking.repositories.BookingRepository;
import com.tuandc.interview.hrs_hotel_booking.repositories.RoomRepository;
import com.tuandc.interview.hrs_hotel_booking.services.BookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void testCreateBooking_success() {
        // Mocking input data for the booking
        Long roomId = 1L;
        String guestName = "John Doe";
        String guestPhone = "123456789";
        LocalDate checkInDate = LocalDate.now().plusDays(1);
        LocalDate checkOutDate = LocalDate.now().plusDays(3);

        // Mocking RoomEntity
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(roomId);

        // Mocking roomRepository behavior
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(roomEntity));

        // Mocking bookingRepository behavior
        when(bookingRepository.findBookedRoomIds(roomId, checkInDate, checkOutDate)).thenReturn(Collections.emptyList());

        BookingEntity mockedSavedBooking = new BookingEntity();
        mockedSavedBooking.setId(1L);
        mockedSavedBooking.setGuestName(guestName);
        mockedSavedBooking.setGuestPhone(guestPhone);
        mockedSavedBooking.setCheckInDate(checkInDate);
        mockedSavedBooking.setCheckOutDate(checkOutDate);
        mockedSavedBooking.setStatus(Status.CONFIRMED);

        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(mockedSavedBooking);

        // Invoke the service method to create a booking
        Booking createdBooking = bookingService.createBooking(roomId, guestName, guestPhone, checkInDate, checkOutDate);

        // Assertions
        assertNotNull(createdBooking);
        assertEquals(guestName, createdBooking.getGuestName());
        assertEquals(guestPhone, createdBooking.getGuestPhone());
        assertEquals(checkInDate, createdBooking.getCheckInDate());
        assertEquals(checkOutDate, createdBooking.getCheckOutDate());
        assertEquals(Status.CONFIRMED, createdBooking.getStatus());
    }

    @Test
    void testCreateBookingRoomNotFound() {
        // Mocking input data for the booking
        Long roomId = 1L;
        String guestName = "John Doe";
        String guestPhone = "123456789";
        LocalDate checkInDate = LocalDate.now().plusDays(1);
        LocalDate checkOutDate = LocalDate.now().plusDays(3);

        // Mocking roomRepository behavior for Room not found
        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        // Assert that NotFoundEntityException is thrown when room is not found
        Assertions.assertThrows(NotFoundEntityException.class, () ->
                bookingService.createBooking(roomId, guestName, guestPhone, checkInDate, checkOutDate)
        );

        // Verify that bookingRepository.save is never called
        verify(bookingRepository, never()).save(any());
    }

    @Test
    void testCreateBookingRoomNotAvailable() {
        // Mocking input data for the booking
        Long roomId = 1L;
        String guestName = "John Doe";
        String guestPhone = "123456789";
        LocalDate checkInDate = LocalDate.now().plusDays(1);
        LocalDate checkOutDate = LocalDate.now().plusDays(3);

        // Mocking RoomEntity
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(roomId);

        // Mocking roomRepository behavior for Room found
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(roomEntity));

        // Mocking bookingRepository behavior for room not available
        when(bookingRepository.findBookedRoomIds(roomId, checkInDate, checkOutDate)).thenReturn(Collections.singletonList(roomId));

        // Assert that BadParameterException is thrown when room is not available
        Assertions.assertThrows(BadParameterException.class, () ->
                bookingService.createBooking(roomId, guestName, guestPhone, checkInDate, checkOutDate)
        );

        // Verify that bookingRepository.save is never called
        verify(bookingRepository, never()).save(any());
    }
}