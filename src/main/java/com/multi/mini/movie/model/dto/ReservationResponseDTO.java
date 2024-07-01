package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ReservationResponseDTO {
	
	private ArrayList<VWResDataGroupDTO> reservations;
	private int count;
	private int pages;
	
	
	
	
	
}
