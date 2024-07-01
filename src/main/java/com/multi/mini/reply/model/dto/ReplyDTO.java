package com.multi.mini.reply.model.dto;

import lombok.Data;

@Data
public class ReplyDTO {
	private int reply_no;
	private int board_no;
	private int member_no;
	private String content;


	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ReplyDTO{" +
				"reply_no=" + reply_no +
				", board_no=" + board_no +
				", member_no=" + member_no +
				", content='" + content + '\'' +
				'}';
	}
}
