package com.multi.mini.reply.controller;


import com.multi.mini.reply.model.dao.ReplyDAO;
import com.multi.mini.reply.model.dto.ReplyDTO;
import com.multi.mini.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //ReplyController replyController = new ReplyController();
@RequiredArgsConstructor
public class ReplyController {

	@Autowired
	ReplyDAO replyDAO;
	
	@Autowired
	ReplyService replyService;
	
	@GetMapping("/board/updateReply")
	public void updateBoard(@RequestParam("no") int no, @RequestParam("comment") String comment, Model model) throws Exception {
		ReplyDTO dto = replyService.selectReply(no);
		model.addAttribute("dto", dto);
		dto.setContent(comment);
		replyService.updateReply(dto);
		
	}
	
	
	
	@RequestMapping("/board/insertReply") //ajax호출!
	//ajax호출은 views/insert4.jsp를 만들지 않으면 ajax호출 404에러가 뜬다. 
	public void insert(ReplyDTO replyDTO, Model model) {
		System.out.println(replyDTO);
		int result = replyDAO.insert(replyDTO);
		System.out.println("reply insert 결과 >>" + result);

		model.addAttribute("result", result);
	}
	
	@RequestMapping("/board/deleteReply") //ajax호출!
	//ajax호출은 views/insert4.jsp를 만들지 않으면 ajax호출 404에러가 뜬다. 
	public String delete(@RequestParam("boardNo") String boardNo ,@RequestParam("no") int no) throws Exception {
		replyService.deleteReply(no);
		return "redirect:/board/view?no=" + boardNo;
	}
	
	
}
