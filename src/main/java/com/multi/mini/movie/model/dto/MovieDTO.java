package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MovieDTO {

	private int movieNo; // 영화번호
	private String movieTitle; // 제목
	private String genreNo;
	private int popularity;
	private int duration; // 상영시간
	private String originalLanguage;
	private String movieContent;
	private int adult;
	private String posterPath;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	
	






}
