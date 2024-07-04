package com.multi.mini.admin.qna.controller;

import com.multi.mini.common.model.dto.EmailDTO;
import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.common.service.PageService;
import com.multi.mini.qna.model.dto.QnaDTO;
import com.multi.mini.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/question")
public class AdminQnaController {
    private final QnaService qnaService;
    private final PageService pageService;

    @GetMapping
    public String showAQuestionList(@RequestParam(required = false, name = "type") String type,
                                    @RequestParam(required = false, name = "keyword") String keyword,
                                    @RequestParam(required = false, defaultValue = "1", name = "page") int page,
                                    Model model) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setStartEnd(page);

        try {
            // 페이지 수 계산
            int questionCount = pageService.selectQuestionCount(type, keyword);
            int pages = (int) Math.ceil((double) questionCount / 10);

            ArrayList<QnaDTO> questions = qnaService.getQuestionsListAll(type, keyword, pageDTO);

            model.addAttribute("questions", questions);

            // 페이징을 위한 파라미터
            model.addAttribute("type", type);
            model.addAttribute("keyword", keyword);
            model.addAttribute("pages", pages);
            model.addAttribute("count", questionCount);
        } catch (Exception e) {
            model.addAttribute("msg", "문의 리스트 조회 실패");
        }
        return "admin/qna/viewQuestions";
    }

    @GetMapping("/find")
    public String showAQuestionList(@RequestParam("no") int questionNo, Model model) {
        try {
            QnaDTO question = qnaService.findQnaByNo(questionNo);
            model.addAttribute("question", question);
        } catch (Exception e) {
            model.addAttribute("msg", "문의 리스트 조회 실패");
        }
        return "admin/qna/viewQuestion";
    }

    @GetMapping("/answer")
    public String showQunestAnswer(@RequestParam("no") int questionNo, Model model) {
        try {
            QnaDTO question = qnaService.findQnaByNo(questionNo);
            model.addAttribute("question", question);
        } catch (Exception e) {
            model.addAttribute("msg", "문의글 조회 실패");
        }

        return "admin/qna/viewAnswer";
    }

    @PostMapping("/answer")
    @Transactional
    public String submitAdminAnswer(EmailDTO emailDTO, @RequestParam("questionNo") int questionNo, RedirectAttributes redirectAttributes) {
        System.out.println("값 받는지 확인 : " + emailDTO + " 문의 번호 : " + questionNo);
        try {
            qnaService.sendAnswerEmail(emailDTO, questionNo);
            redirectAttributes.addFlashAttribute("msg", "답변 완료");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "답변 실패");
        }
        return "redirect:/admin/question";
    }
}
//return "redirect:/admin/question";
