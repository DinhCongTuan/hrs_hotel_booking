package com.tuandc.interview.hrs_hotel_booking.controller;

import com.tuandc.interview.hrs_hotel_booking.entity.HotelEntity;
import com.tuandc.interview.hrs_hotel_booking.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController extends BaseController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelEntity> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // Other endpoints for hotel operations (create, update, delete, etc.)
}

