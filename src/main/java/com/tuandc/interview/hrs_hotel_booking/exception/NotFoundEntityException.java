package com.tuandc.interview.hrs_hotel_booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends HrsHotelBookingException {
    public NotFoundEntityException()
    {
        super();
    }
    public NotFoundEntityException(String message)
    {
        super(message);
    }
    public NotFoundEntityException(Throwable throwable)
    {
        super(throwable);
    }
    public NotFoundEntityException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
