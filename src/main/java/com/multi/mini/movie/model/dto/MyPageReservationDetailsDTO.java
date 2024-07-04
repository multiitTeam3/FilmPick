package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class MyPageReservationDetailsDTO {
    private int rouNum;
    private int memberNo;
    private String movieTitle;
    private String cinemaName;
    private String screenName;
    private String seatNum;
    private int totalPrice;
    private String startTime;
    private String EndTime;
    private Date showDate;
    private Date rsvDate;
}
