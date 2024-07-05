package com.multi.mini.movie.model.dto;

import lombok.Data;

@Data
public class MoviePageDTO {
	
	private int page;
	private int start;
	private int end;
	
	public void setStartEnd(int page) {
		
		start = 1 + (page - 1) * 3;
		end = page * 3;
		
		
	}
	
	
}
