package com.multi.mini.board.controller;

import com.multi.mini.board.model.dto.BoardDTO;
import com.multi.mini.board.service.BoardService;
import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.common.service.PageService;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.member.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final PageService pageService;


    @GetMapping
    public String listBoard(PageDTO page, Model model) {
        page.setPage(1);
        page.setStartEnd(page.getPage());
        try {
            int count = pageService.selectBoardCount(page);
            int pages = count / 10 + 1;

            List<BoardDTO> boards = boardService.selectBoardAll(page);

            model.addAttribute("count", count);
            model.addAttribute("pages", pages);
            model.addAttribute("boards", boards);

            for (BoardDTO board : boards) {
                System.out.println(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("board list error : " + e);
        }

        return "board/listBoard";
    }

    @GetMapping("/view")
    public String findUser(@RequestParam("no") int no, Model model) throws Exception{
        try {
            BoardDTO boardData = boardService.findBoardByNo(no);
            model.addAttribute("board", boardData);
        } catch (Exception e) {
            model.addAttribute("msg", "게시글 조회 실패");
        }
        return "board/viewBoard";
    }


    @GetMapping("/insert")
    public String showInsertForm(Model model) {
        BoardDTO boardData = new BoardDTO();
        boardData.setWriter(new MemberDTO());

        model.addAttribute("board", boardData);
        return "board/insertBoard";
    }

    @PostMapping("/insert")
    public String insertBoard(@ModelAttribute BoardDTO boardData) {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 작성자의 memberNo와 userName을 설정
        MemberDTO writer = new MemberDTO();
        writer.setMemberNo(userDetails.getMemberNo());
        writer.setUserName(userDetails.getUserName());
        boardData.setWriter(writer);

        try {
            boardService.insertBoard(boardData);
        } catch (Exception e) {
            System.out.println("board insert error : " + e);
        }

        // 서비스 계층에 게시글 저장 요청
        return "redirect:/board";
    }

    @GetMapping("/delete")
    public String deleteBoard(@RequestParam("no") int no, Model model) throws Exception{
        try {
            boardService.deleteBoard(no);
            model.addAttribute("msg", "게시글 삭제 성공");
        } catch (Exception e) {
            model.addAttribute("msg", "게시글 삭제 실패");
        }
        return "redirect:/board/listBoard";
    }


}
