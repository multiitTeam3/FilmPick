package com.multi.mini.movie.model.dto;


import lombok.Data;

@Data
public class MovieScheduleDTO {
	
	private int scheduleNo;
	private int movieNo;
	private int cinemaNo;
	private String screenCode;
	private String date;
	private String startTime;
	private String endTime;
	
	
	
	
}
