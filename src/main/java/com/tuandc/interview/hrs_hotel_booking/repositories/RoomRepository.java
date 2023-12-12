package com.tuandc.interview.hrs_hotel_booking.repositories;

import com.tuandc.interview.hrs_hotel_booking.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @Query("SELECT r FROM RoomEntity r WHERE r.hotel.id = :hotelId AND r.id NOT IN :bookedRoomIds")
    List<RoomEntity> findAvailableRoomsForHotel(
            @Param("hotelId") Long hotelId,
            @Param("bookedRoomIds") List<Long> bookedRoomIds
    );
}
