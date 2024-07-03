package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ReservationDataDTO {
	
	private int scheduleNo;
	private ArrayList<Integer> selectedSeatNoList;
	private int adult;
	private int teen;
	
	
	
	
}
