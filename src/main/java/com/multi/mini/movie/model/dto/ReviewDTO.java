package com.multi.mini.movie.model.dto;

import lombok.Data;
import org.codehaus.groovy.transform.SourceURIASTTransformation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ReviewDTO {
	
	private int reviewNo;
	private int movieNo;
	private int memberNo;
	private String content;
	private int rate;
	private Timestamp createDate;
	

}
