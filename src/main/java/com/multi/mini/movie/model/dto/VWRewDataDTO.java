package com.multi.mini.movie.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class VWRewDataDTO {
	
	private int reviewNo;
	private int movieNo;
	private int memberNo;
	private String content;
	private int rate;
	private Timestamp createDate;
	private String movieTitle;
	private String posterPath;
	private String userName;
	
	
	
	
}
