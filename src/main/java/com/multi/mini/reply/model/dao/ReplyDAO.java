package com.multi.mini.reply.model.dao;


import com.multi.mini.reply.model.dto.ReplyDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyDAO {

	@Autowired
	SqlSessionTemplate sqlsession;//100
	
	
	public int insert(ReplyDTO dto) {
		return sqlsession.insert("com.multi.mini.reply.model.mapper.ReplyMapper.create", dto);
	}
	
	public List<ReplyDTO> list(int board_no) {
		return sqlsession.selectList("com.multi.mini.reply.model.mapper.ReplyMapper.list", board_no);
	}

	public ReplyDTO selectReply(SqlSessionTemplate sqlSession, int no) {
		return sqlSession.selectOne("com.multi.mini.reply.model.mapper.ReplyMapper.selectReply", no);
	}

	public int updateBoard(SqlSessionTemplate sqlSession, ReplyDTO dto) {
		return sqlSession.insert("com.multi.mini.reply.model.mapper.ReplyMapper.updateReply", dto);
	}

	public int deleteReply(SqlSessionTemplate sqlSession, int no) {
		return sqlSession.delete("com.multi.mini.reply.model.mapper.ReplyMapper.deleteReply", no);
	}
}
