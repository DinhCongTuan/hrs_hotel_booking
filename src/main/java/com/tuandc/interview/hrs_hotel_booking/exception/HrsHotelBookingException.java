package com.tuandc.interview.hrs_hotel_booking.exception;

import lombok.Data;

@Data
public class HrsHotelBookingException extends RuntimeException {

    private Object data;
    public HrsHotelBookingException(Object data) {
        super();
        this.data = data;
    }
    public HrsHotelBookingException() {
        super();
    }
    public HrsHotelBookingException(String message) {
        super(message);
    }
    public HrsHotelBookingException(Throwable throwable) {
        super(throwable);
    }
    public HrsHotelBookingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
