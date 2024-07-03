package com.multi.mini.like.service;


public interface LikeService {

	int likeCheck(int no, String login) throws Exception;

	void insertLike(int no, String login) throws Exception;

	void updateLike(int no) throws Exception;

	void updateLikeCancel(int no) throws Exception;

	void deleteLike(int no, String login) throws Exception;

	

	
	
}
