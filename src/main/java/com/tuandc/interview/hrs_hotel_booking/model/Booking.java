package com.tuandc.interview.hrs_hotel_booking.model;

import com.tuandc.interview.hrs_hotel_booking.entity.BookingEntity;
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
    private String guestPhone;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Status status;

    public Booking(BookingEntity bookingEntity) {
        this.id = bookingEntity.getId();
        this.guestName = bookingEntity.getGuestName();
        this.checkInDate = bookingEntity.getCheckInDate();
        this.checkOutDate = bookingEntity.getCheckOutDate();
        this.guestPhone = bookingEntity.getGuestPhone();
        this.status = bookingEntity.getStatus();
    }

//    private Room mapRoomEntityToModel(RoomEntity roomEntity) {
//        Room room = new Room();
//        // Mapping logic from RoomEntity to Room model
//        return room;
//    }
}
