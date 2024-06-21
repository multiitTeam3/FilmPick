package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MovieDTO {

	private int movieNo;
	private String movieTitle;
	private String genreNo;
	private int popularity;
	private int duration;
	private String originalLanguage;
	private String movieContent;
	private int adult;
	private String posterPath;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	
	






}
