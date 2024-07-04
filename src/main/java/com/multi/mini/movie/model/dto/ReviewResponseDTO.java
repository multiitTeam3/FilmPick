package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ReviewResponseDTO {
	
	private ArrayList<VWRewDataDTO> reviews;
	private int count;
	private int pages;
	
	
	
	
	
}
