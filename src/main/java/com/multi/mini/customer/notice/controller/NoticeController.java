package com.multi.mini.customer.notice.controller;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.customer.notice.model.dto.NoticeDTO;
import com.multi.mini.customer.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/Customer")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/noticeInfo")
    public String showNoticeAll(Model model) {
        return "common/customer/notice/view-notice";
    }

    @GetMapping("/loadNotice")
    public String loadNotice(@RequestParam(required = false, value = "type") String type,
                             @RequestParam(required = false, value = "keyword") String keyword,
                             @RequestParam(required = false, value = "page") int page, Model model) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setStartEnd(page);

        try {
            ArrayList<NoticeDTO> notices = noticeService.getNoticeList(type, keyword, pageDTO);
            model.addAttribute("notices", notices);
        } catch (Exception e) {
            model.addAttribute("msg", "공지사항 목록 조회 실패");
        }
        return "common/customer/notice/notice";
    }

    @GetMapping("/NoticeDetail")
    public String showNotice(@RequestParam("no") int noticeNo, Model model) {
        try {
            NoticeDTO notice = noticeService.getNotice(noticeNo);
            model.addAttribute("notice", notice);

        } catch (Exception e) {
            model.addAttribute("msg", "공지사항 조회 실패");
        }
        return "common/customer/notice/NoticeDetail";
    }

}
