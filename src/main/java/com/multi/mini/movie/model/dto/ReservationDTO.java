package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDTO {
	
	private int rsvNo;
	private int scheduleNo;
	private int seatNo;
	private int memberNo;
	private int rsvMoviePrice;
	private String rsvIsPaid;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	
	
	
}
