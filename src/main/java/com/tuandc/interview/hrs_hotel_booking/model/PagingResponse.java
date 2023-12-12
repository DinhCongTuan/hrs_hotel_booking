package com.tuandc.interview.hrs_hotel_booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PagingResponse<T> {

    @JsonProperty(value = "total_records")
    private long totalRecords;

    @JsonProperty(value = "total_pages")
    private int totalPages;

    private T content;
}
