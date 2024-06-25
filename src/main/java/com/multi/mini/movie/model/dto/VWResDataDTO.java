package com.multi.mini.movie.model.dto;

import lombok.Data;

@Data
public class VWResDataDTO {
	
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
	private String adult;
	private String seatName;
	private int rsvMoviePrice;
	
	
	
	
}
