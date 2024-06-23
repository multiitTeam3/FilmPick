package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

@Data
public class VWResDataGroupDTO {
	
	
	private int scheduleNo;
	private int memberNo;
	private String posterPath;
	private int movieNo;
	private String movieTitle;
	
	private String rsvIsPaid;
	
	private String date;
	private String startTime;
	private String endTime;
	private String cinemaName;
	private String screenName;
	private String adult;
	
	private int adultNum;
	private int teenNum;
	private int totalPrice;
	
	//가져온 합쳐진 좌석 정보를 리스트로 변환
	private String seatNames;
	private ArrayList<String> seatNameList;
	
	
	public void setSeatNames(String seatNames){
		
		this.seatNames=seatNames;
		this.seatNameList = seatNames != null? new ArrayList<>(Arrays.asList(seatNames.split(","))) : new ArrayList<>();
		
	}
	
	
	
}
