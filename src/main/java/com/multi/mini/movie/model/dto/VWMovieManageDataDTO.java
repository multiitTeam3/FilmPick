package com.multi.mini.movie.model.dto;

import lombok.Data;

@Data
public class VWMovieManageDataDTO {
	
	private int movieNo;
	private String movieTitle;
	private int genreNo;
	private int popularity;
	private int duration;
	private String originalLanguage;
	private String movieContent;
	private int adult;
	private String posterPath;
	private String genreContent;
	private double avgRate;
	private int ticketSold;
	
	
	
	
}
