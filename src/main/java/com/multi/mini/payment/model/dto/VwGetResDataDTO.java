package com.multi.mini.payment.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VwGetResDataDTO {
    private int rsvNo;
    private int scheduleNo;
    private int seatNo;
    private int memberNo;
    private String rsvIsPaid;
    private String posterPath;
    private String movieTitle;
    private String date;
    private String startTime;
    private String endTime;
    private String cinemaName;
    private String screenName;
    private boolean adult;
    private String seatName;
    private int rsvMoviePrice;



}
