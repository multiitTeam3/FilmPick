package com.multi.mini.community.controller;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.common.service.PageService;
import com.multi.mini.community.model.dto.BoardDTO;
import com.multi.mini.community.service.BoardService;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.reply.model.dao.ReplyDAO;
import com.multi.mini.reply.model.dto.ReplyDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class BoardController {
    private final BoardService boardService;
    private final PageService pageService;
    private final ReplyDAO dao2;


    @GetMapping("/updateBoard")
    public void updateBoard(@RequestParam("no") int no, Model model) throws Exception {
        BoardDTO dto = boardService.findBoardByNo(no);
        model.addAttribute("dto", dto);

    }

    @GetMapping("/list")
    public String listBoard(@RequestParam(value = "page", required = false, defaultValue = "1")  int page, Model model) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setStartEnd(pageDTO.getPage());
        try {
            int count = pageService.selectBoardCount(pageDTO);
            int pages = count / 10 + 1;

            List<BoardDTO> boards = boardService.selectBoardAll(pageDTO);

            model.addAttribute("count", count);
            model.addAttribute("pages", pages);
            model.addAttribute("boards", boards);
            model.addAttribute("currentPage", page);

            for (BoardDTO board : boards) {
                System.out.println(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("board list error : " + e);
        }

        return "community/listBoard";
    }

    @GetMapping("/view")
    public String findUser(@RequestParam("no") int no, Model model) throws Exception{
        try {
            BoardDTO boardData = boardService.findBoardByNo(no);
            List<ReplyDTO> list = dao2.list(boardData.getBoardNo());
            model.addAttribute("board", boardData);
            model.addAttribute("list", list);
        } catch (Exception e) {
            model.addAttribute("msg", "게시글 조회 실패");
        }
        return "community/viewBoard";
    }


    @GetMapping("/insert")
    public String showInsertForm(Model model) {
        BoardDTO boardData = new BoardDTO();
        boardData.setWriter(new MemberDTO());

        model.addAttribute("board", boardData);
        return "community/insertBoard";
    }

    @PostMapping("/insert")
    public String insertBoard(@ModelAttribute BoardDTO boardData, HttpServletRequest request, @RequestPart("singleFile") MultipartFile singleFile, Model model) {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 작성자의 memberNo와 userName을 설정
        MemberDTO writer = new MemberDTO();
        writer.setMemberNo(userDetails.getMemberNo());
        writer.setUserName(userDetails.getUsername());
        boardData.setWriter(writer);

        /* 파일을 저장할 경로 설정 */
        String path = request.getSession().getServletContext().getRealPath("");
        String root = path.substring(0,path.length() - 7);
        String filePath = root + "resources/static/img/board";

        File mkdir = new File(filePath);
        if (!mkdir.exists()) {
            mkdir.mkdirs();
        }

        /* 파일명 변경 처리 */
        String originFileName = singleFile.getOriginalFilename();
        if(originFileName != null && !originFileName.isEmpty()){
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            /* 파일을 저장한다. */
            try {
                singleFile.transferTo(new File(filePath + "\\" + savedName));
                model.addAttribute("savedName", savedName);
                boardData.setImg(savedName);
                boardService.insertBoard(boardData);
            } catch (Exception e) {
                System.out.println("board insert error : " + e);
                /* 실패시 파일 삭제 */
                new File(filePath + "\\" + savedName).delete();
                model.addAttribute("message", "파일 업로드 실패!!");
            }
        }
        else{
            try {
                boardService.insertBoard(boardData);
            } catch (Exception e) {
                System.out.println("board insert error : " + e);
            }
        }


        // 서비스 계층에 게시글 저장 요청
        return "redirect:/community/list";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("no") int no, Model model) throws Exception{
        try {
            System.out.println("-------------------------------------" + no);
            boardService.deleteBoard(no);
            model.addAttribute("msg", "게시글 삭제 성공");
        } catch (Exception e) {
            model.addAttribute("msg", "게시글 삭제 실패");
        }
        return "redirect:/community/list";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(BoardDTO boardDTO) throws Exception {
        boardService.updateBoard(boardDTO);
        return "redirect:/community/list";
    }




}
