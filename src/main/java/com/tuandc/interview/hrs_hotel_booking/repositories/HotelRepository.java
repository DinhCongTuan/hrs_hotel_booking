package com.tuandc.interview.hrs_hotel_booking.repositories;

import com.tuandc.interview.hrs_hotel_booking.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    // Add custom query methods if needed
}
