package com.multi.mini.admin.ticketing.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TicketingDTO {
    private int rowNum;
    private int rsvNo;
    private String isPaid;
    private String cinemaName;
    private String screenName;
    private String email;
    private String movieTitle;
    private String poster;
    private String seatName;
    private int price;
    private Date createDate;
}

