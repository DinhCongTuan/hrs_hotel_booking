package com.tuandc.interview.hrs_hotel_booking.services;

import com.tuandc.interview.hrs_hotel_booking.entity.BookingEntity;
import com.tuandc.interview.hrs_hotel_booking.entity.RoomEntity;
import com.tuandc.interview.hrs_hotel_booking.exception.BadParameterException;
import com.tuandc.interview.hrs_hotel_booking.exception.NotFoundEntityException;
import com.tuandc.interview.hrs_hotel_booking.model.Booking;
import com.tuandc.interview.hrs_hotel_booking.model.Status;
import com.tuandc.interview.hrs_hotel_booking.repositories.BookingRepository;
import com.tuandc.interview.hrs_hotel_booking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public Page<BookingEntity> getAllBookingsOrderedByCheckInDateDesc(Pageable pageable) {
        return bookingRepository.findAllByOrderByCheckInDateDesc(pageable);
    }

    public Booking createBooking(Long roomId, String guestName, String guestPhone,
                                 LocalDate checkInDate, LocalDate checkOutDate) {
        validateBooking(checkInDate, checkOutDate);
        RoomEntity room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            throw new NotFoundEntityException();
        }

        // Check room availability before booking
        if (!isRoomAvailable(roomId, checkInDate, checkOutDate)) {
            throw new BadParameterException("This room is not available");
        }
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setRoom(room);
        bookingEntity.setGuestName(guestName);
        bookingEntity.setGuestPhone(guestPhone);
        bookingEntity.setCheckInDate(checkInDate);
        bookingEntity.setCheckOutDate(checkOutDate);
        bookingEntity.setStatus(Status.CONFIRMED); // set initial status as per your logic

        BookingEntity saved = bookingRepository.save(bookingEntity);
        return new Booking(saved);
    }

    public List<BookingEntity> findBookingsByGuestName(String guestName, LocalDate checkInDate, LocalDate checkOutDate) {
        // Implement logic to retrieve bookings by guest name within the date range from the repository
        // You can use your existing repository method or write a custom query here
        return bookingRepository
                .findByGuestNameAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(guestName, checkInDate, checkOutDate);
    }

    private boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Long> bookedRoomIds = bookingRepository.findBookedRoomIds(roomId, checkInDate, checkOutDate);
        return bookedRoomIds.isEmpty(); // Room is available if no bookings exist for the specified date range
    }
    private void validateBooking(LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkInDate.isBefore(LocalDate.now())
                || checkInDate.isAfter(checkOutDate.plusDays(1))) {
            throw new BadParameterException("CheckIn/CheckOut dates were not valid");
        }
    }

}

