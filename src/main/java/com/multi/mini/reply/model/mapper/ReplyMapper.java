package com.multi.mini.reply.model.mapper;


import com.multi.mini.reply.model.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {

	ReplyDTO selectReply(int no) throws Exception;

	void updateReply(ReplyDTO dto) throws Exception;

	void deleteReply(int no) throws Exception;
}
