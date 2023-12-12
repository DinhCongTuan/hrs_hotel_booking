package com.tuandc.interview.hrs_hotel_booking.services;

import com.tuandc.interview.hrs_hotel_booking.entity.HotelEntity;
import com.tuandc.interview.hrs_hotel_booking.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelEntity> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Other methods related to hotel business logic
}
