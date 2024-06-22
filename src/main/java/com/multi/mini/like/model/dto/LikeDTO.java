package com.multi.mini.like.model.dto;

import lombok.Data;

@Data
public class LikeDTO {
	private int like_no;
	private int board_no;
	private int member_no;
	
	public int getLike_no() {
		return like_no;
	}
	public void setLike_no(int like_no) {
		this.like_no = like_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	
	@Override
	public String toString() {
		return "LikeDTO [like_no=" + like_no + ", board_no=" + board_no + ", member_no=" + member_no + "]";
	}
	
	
}
