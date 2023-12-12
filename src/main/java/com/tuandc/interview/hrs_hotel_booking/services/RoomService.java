package com.tuandc.interview.hrs_hotel_booking.services;

import com.tuandc.interview.hrs_hotel_booking.entity.RoomEntity;
import com.tuandc.interview.hrs_hotel_booking.repositories.BookingRepository;
import com.tuandc.interview.hrs_hotel_booking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<RoomEntity> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<RoomEntity> getAvailableRoomsForHotel(Long hotelId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Long> bookedRoomIds = bookingRepository.findBookedRoomIdsForHotel(hotelId, checkInDate, checkOutDate);
        return roomRepository.findAvailableRoomsForHotel(hotelId, bookedRoomIds);
    }
}
