package com.tuandc.interview.hrs_hotel_booking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Hotel {
    private Long id;
    private String name;
    private String address;
}
