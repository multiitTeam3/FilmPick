package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class MyPageReservationDetailsDTO {
    private int rowNum;
    private int memberNo;
    private String movieTitle;
    private String cinemaName;
    private String screenName;
    private String seatName;
    private int totalPrice;
    private String startTime;
    private String EndTime;
    private Date date;
    private Date dayDate;
}
