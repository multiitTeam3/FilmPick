package com.multi.mini.customer.notice.controller;

import com.multi.mini.admin.notice.service.AdminNoticeService;
import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.common.service.PageService;
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
    private final AdminNoticeService adminNoticeService;
    private final NoticeService noticeService;
    private final PageService pageService;

    @GetMapping(value = {"/Index", "/noticeInfo"})
    public String showNoticeAll(@RequestParam(required = false, name = "type") String type,
                                @RequestParam(required = false, name = "keyword") String keyword,
                                @RequestParam(required = false, defaultValue = "1", name = "page") int page,
                                Model model) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setStartEnd(page);

        try {
            // 페이지 수 계산
            int noticeCount = pageService.selectNoticeCount(type, keyword);
            int pages = (int) Math.ceil((double) noticeCount / 10);

            ArrayList<NoticeDTO> notices = adminNoticeService.getNoticeList(type, keyword, pageDTO);

            model.addAttribute("notices", notices);

            // 페이징을 위한 파라미터
            model.addAttribute("type", type);
            model.addAttribute("keyword", keyword);
            model.addAttribute("pages", pages);
            model.addAttribute("count", noticeCount);
        } catch (Exception e) {
            model.addAttribute("msg", "공지사항 조회 실패");
        }
        return "common/customer/notice/notice";
    }

//    @GetMapping("/loadNotice")
//    public String loadNotice(@RequestParam(required = false, name = "type") String type,
//                             @RequestParam(required = false, name = "keyword") String keyword,
//                             @RequestParam(required = false, defaultValue = "1", name = "page") int page,
//                             Model model) {
//        PageDTO pageDTO = new PageDTO();
//        pageDTO.setStartEnd(page);
//
//        try {
//            // 페이지 수 계산
//            int memberCount = pageService.selectNoticeCount(type, keyword);
//            int pages = (int) Math.ceil((double) memberCount / 10);
//
//            ArrayList<NoticeDTO> notices = noticeService.getNoticeList(type, keyword, pageDTO);
//            model.addAttribute("notices", notices);
//
//            // 페이징을 위한 파라미터
//            model.addAttribute("type", type);
//            model.addAttribute("keyword", keyword);
//            model.addAttribute("pages", pages);
//            model.addAttribute("count", memberCount);
//        } catch (Exception e) {
//            model.addAttribute("msg", "공지사항 목록 조회 실패");
//        }
//        return "common/customer/notice/notice";
//    }

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
