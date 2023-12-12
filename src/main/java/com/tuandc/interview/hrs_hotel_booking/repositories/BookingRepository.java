package com.tuandc.interview.hrs_hotel_booking.repositories;

import com.tuandc.interview.hrs_hotel_booking.entity.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    @Query("SELECT b.room.id FROM BookingEntity b WHERE b.room.hotel.id = :hotelId " +
            "AND ((b.checkInDate BETWEEN :checkInDate AND :checkOutDate) " +
            "OR (b.checkOutDate BETWEEN :checkInDate AND :checkOutDate))")
    List<Long> findBookedRoomIdsForHotel(
            @Param("hotelId") Long hotelId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );

    @Query("SELECT b.room.id FROM BookingEntity b WHERE b.room.id = :roomId " +
            "AND ((b.checkInDate BETWEEN :checkInDate AND :checkOutDate) " +
            "OR (b.checkOutDate BETWEEN :checkInDate AND :checkOutDate))")
    List<Long> findBookedRoomIds(
            @Param("roomId") Long roomId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );

    List<BookingEntity> findByGuestNameAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
            String guestName,
            LocalDate checkInDate,
            LocalDate checkOutDate
    );

    Page<BookingEntity> findByGuestNameAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
            String guestName,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            Pageable pageable
    );

    Page<BookingEntity> findAllByOrderByCheckInDateDesc(Pageable pageable);
}