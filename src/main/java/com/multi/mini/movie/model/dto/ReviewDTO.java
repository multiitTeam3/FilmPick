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
	private String formattedDate;
	
	public ReviewDTO() {
		
		formatDate();
	}
	
	public ReviewDTO(Timestamp createDate) {
		this.createDate = createDate;
		formatDate();
	}
	
	public ReviewDTO(int reviewNo, int movieNo, int memberNo, int rate, String content, Timestamp createDate) {
		this.reviewNo = reviewNo;
		this.movieNo = movieNo;
		this.memberNo = memberNo;
		this.rate = rate;
		this.content = content;
		this.createDate = createDate;
		
		formatDate();
		
	}
	
	public void formatDate(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.formattedDate = sdf.format(this.createDate);
		
		System.out.println("this.formattedDate : " + this.formattedDate);

	}
	
	
	

}
