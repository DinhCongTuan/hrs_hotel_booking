package com.tuandc.interview.hrs_hotel_booking.controller;


import com.tuandc.interview.hrs_hotel_booking.exception.BadParameterException;
import com.tuandc.interview.hrs_hotel_booking.exception.NotFoundEntityException;
import com.tuandc.interview.hrs_hotel_booking.model.BaseResponse;
import com.tuandc.interview.hrs_hotel_booking.model.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse<Object> handleException(Exception ex) {
        return new BaseResponse<>(StatusCode.SERVER_ERROR, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotFoundEntityException.class)
    public BaseResponse<String> handleNotFoundEntityException(NotFoundEntityException ex) {
        return new BaseResponse<>(StatusCode.NOT_FOUND, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadParameterException.class)
    public BaseResponse<String> handleBadParameterException(BadParameterException ex) {
        return new BaseResponse<>(StatusCode.BAD_PARAMETER, ex.getMessage());
    }
}
