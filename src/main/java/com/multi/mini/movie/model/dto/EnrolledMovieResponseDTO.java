package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class EnrolledMovieResponseDTO {
	
	private ArrayList<VWMovieManageDataDTO> list;
	private int pages;
	private int count;
	
	
	
}
