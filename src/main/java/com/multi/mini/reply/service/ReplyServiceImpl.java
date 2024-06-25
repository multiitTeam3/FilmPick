package com.multi.mini.reply.service;


import com.multi.mini.reply.model.dao.ReplyDAO;
import com.multi.mini.reply.model.dto.ReplyDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("replyService")
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO replyDAO;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public ReplyDTO selectReply(int no) throws Exception {
		ReplyDTO dto = replyDAO.selectReply(sqlSession, no);
		return dto;
	}

	@Override
	public void updateReply(ReplyDTO dto) throws Exception {
		int result = replyDAO.updateBoard(sqlSession, dto);
		if(result < 0) {
			throw new Exception("댓글 수정에 실패하셨습니다");
		}
	}

	@Override
	public void deleteReply(int no) throws Exception {
		int result = replyDAO.deleteReply(sqlSession, no);
		if(result < 0) {
			throw new Exception("댓글 삭제에 실패하셨습니다");
		}
	}
	

	
}
