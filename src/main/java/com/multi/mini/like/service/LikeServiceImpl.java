package com.multi.mini.like.service;

import com.multi.mini.like.model.dao.LikeDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("likeService")
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private LikeDAO dao;
	
	@Override
	public int likeCheck(int no, String login) throws Exception {
		return dao.likeCheck(no, login);
	}

	@Override
	public void insertLike(int no, String login) throws Exception {
		dao.insertLike(no, login);
		
	}

	@Override
	public void updateLike(int no) throws Exception {
		dao.updateLike(no);
	}


	@Override
	public void updateLikeCancel(int no) throws Exception {
		dao.updateLikeCancel(no);
	}

	@Override
	public void deleteLike(int no, String login) throws Exception {
		dao.deleteLike(no, login);
	}
	
	
	

	
}
