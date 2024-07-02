package com.multi.mini.reply.service;


import com.multi.mini.reply.model.dto.ReplyDTO;

public interface ReplyService {

	ReplyDTO selectReply(int no) throws Exception;

	void updateReply(ReplyDTO dto) throws Exception;

	void deleteReply(int no) throws Exception;

	
	
}
