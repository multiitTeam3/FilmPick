package com.multi.mini.qna.controller;

import com.multi.mini.common.service.PageService;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.qna.model.dto.QnaDTO;
import com.multi.mini.qna.service.QnaService;
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
@RequestMapping("/qna")
public class QnaController {

    private final QnaService qnaService;
    private final PageService pageService;


    @GetMapping("/insert")
    public String showInsertForm(Model model) {
        QnaDTO qnaDTO = new QnaDTO();

        model.addAttribute("qna", qnaDTO);
        return "qna/insertQna";
    }

    @PostMapping("/insert")
    public String insertBoard(@ModelAttribute QnaDTO qnaDTO, HttpServletRequest request, @RequestPart("singleFile") MultipartFile singleFile, Model model, @RequestParam("category") int category) {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        qnaDTO.setCategory(category);

        // 작성자의 memberNo와 userName을 설정
        qnaDTO.setMember_no(userDetails.getMemberNo());
        qnaDTO.setWriter(userDetails.getUsername());

        /* 파일을 저장할 경로 설정 */
        String path = request.getSession().getServletContext().getRealPath("");
        String root = path.substring(0,path.length() - 7);
        String filePath = root + "resources/static/img/qna";

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
                qnaDTO.setImg(savedName);
                qnaService.insertQna(qnaDTO);
            } catch (Exception e) {
                System.out.println("qna insert error : " + e);
                /* 실패시 파일 삭제 */
                new File(filePath + "\\" + savedName).delete();
                model.addAttribute("message", "파일 업로드 실패!!");
            }
        }
        else{
            try {
                qnaService.insertQna(qnaDTO);
            } catch (Exception e) {
                System.out.println("qna insert error : " + e);
            }
        }


        // 서비스 계층에 게시글 저장 요청
        return "redirect:/home";
    }


    @GetMapping("/list")
    public String listBoard(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        try {
            int member_no = userDetails.getMemberNo();

            List<QnaDTO> qna = qnaService.selectQnaAll(member_no);

            model.addAttribute("qna", qna);

            for (QnaDTO qnaDTO : qna) {
                System.out.println(qnaDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("qna list error : " + e);
        }

        return "qna/listQna";
    }

    @GetMapping("/view")
    public String findUser(@RequestParam("no") int no, Model model) throws Exception{
        try {
            QnaDTO qnaDTO = qnaService.findQnaByNo(no);
            model.addAttribute("qna", qnaDTO);
        } catch (Exception e) {
            model.addAttribute("msg", "문의글 상세 조회 실패");
        }
        return "qna/viewQna";
    }





}
