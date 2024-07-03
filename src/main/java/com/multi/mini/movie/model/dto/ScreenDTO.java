package com.multi.mini.movie.model.dto;

import lombok.Data;

@Data
public class ScreenDTO {
    private int cinemaNo;
    private String screenCode;
    private String screenName;
    private int totalSeat;
    private char isAvail;
}
