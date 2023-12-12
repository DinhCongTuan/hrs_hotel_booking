package com.tuandc.interview.hrs_hotel_booking.model;

import com.tuandc.interview.hrs_hotel_booking.entity.BookingEntity;
import com.tuandc.interview.hrs_hotel_booking.entity.RoomEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class Booking {
    private Long id;
    private String guestName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Room bookedRoom;

    public Booking(BookingEntity bookingEntity) {
        this.id = bookingEntity.getId();
        this.guestName = bookingEntity.getGuestName();
        this.checkInDate = bookingEntity.getCheckInDate();
        this.checkOutDate = bookingEntity.getCheckOutDate();
        // Assuming there's a method to map BookingEntity's room to Room model
//        this.bookedRoom = mapRoomEntityToModel(bookingEntity.getRoom());
    }

//    private Room mapRoomEntityToModel(RoomEntity roomEntity) {
//        Room room = new Room();
//        // Mapping logic from RoomEntity to Room model
//        return room;
//    }
}
