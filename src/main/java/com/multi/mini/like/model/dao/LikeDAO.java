package com.multi.mini.like.model.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;



@Repository
public class LikeDAO {
	
	@Autowired
	SqlSessionTemplate sqlsession;

	public int likeCheck(int no, String login) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_no", login);
		map.put("board_no", no);
		return sqlsession.selectOne("likeMapper.likeCheck", map);
	}

	public void insertLike(int no, String login) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_no", login);
		map.put("board_no", no);
		sqlsession.insert("likeMapper.insertLike", map);
	}

	public void updateLike(int no) {
		sqlsession.update("likeMapper.updateLike", no);
	}


	public void updateLikeCancel(int no) {
		sqlsession.update("likeMapper.updateLikeCancel", no);
	}

	public void deleteLike(int no, String login) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_no", login);
		map.put("board_no", no);
		sqlsession.delete("likeMapper.deleteLike", map);
	}

	


	

}
