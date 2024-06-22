package com.multi.mini.like.controller;

import com.multi.mini.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller //ReplyController replyController = new ReplyController();
public class LikeController {

	@Autowired
	LikeService service;
	
	
	
	
	@GetMapping("/board/updateLike")
	//ajax호출은 views/insert4.jsp를 만들지 않으면 ajax호출 404에러가 뜬다. 
	public void updateLike(@RequestParam("no") int no, @RequestParam("login") String login) throws Exception{
		int likeCheck = service.likeCheck(no, login);
		if(likeCheck == 0) {
			//좋아요 처음누름
			service.insertLike(no, login); //like테이블 삽입
			service.updateLike(no);	//게시판테이블 +1
		}else if(likeCheck == 1) {
            service.updateLikeCancel(no); //게시판테이블 - 1 
			service.deleteLike(no, login); //like테이블 삭제
		}
		
		
	}
	
	
	
}

