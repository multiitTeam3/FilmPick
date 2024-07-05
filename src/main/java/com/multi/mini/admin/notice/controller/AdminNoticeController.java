package com.multi.mini.admin.notice.controller;

import com.multi.mini.admin.notice.service.AdminNoticeService;
import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.common.service.PageService;
import com.multi.mini.customer.notice.model.dto.NoticeCategoryDTO;
import com.multi.mini.customer.notice.model.dto.NoticeDTO;
import com.multi.mini.customer.notice.service.NoticeService;
import com.multi.mini.member.model.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/notice")
public class AdminNoticeController {
    private final AdminNoticeService adminNoticeService;
    private final NoticeService noticeService;
    private final PageService pageService;

    @GetMapping
    public String showAdminNotice(@RequestParam(required = false, name = "type") String type,
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
        return "admin/notice/Notice-List";
    }

    @GetMapping("/insert")
    public String showNoticeForm(NoticeDTO noticeDTO, Model model) {
        try {
            ArrayList<NoticeCategoryDTO> categories = noticeService.getCategoryList();

            model.addAttribute("notice", noticeDTO);
            model.addAttribute("categories", categories);
        } catch (Exception e) {
            model.addAttribute("msg", "카테고리 조회에 실패했습니다");
        }
        return "admin/notice/InsertNoticeForm";
    }

    @PostMapping("/insert")
    public String createNotice(NoticeDTO noticeDTO, @RequestParam("categoryNo") int cetegoryNo, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        NoticeCategoryDTO noticeCategoryDTO = new NoticeCategoryDTO();
        noticeCategoryDTO.setCategoryNo(cetegoryNo);

        noticeDTO.setMemberNo(userDetails.getMemberNo());
        noticeDTO.setCategory(noticeCategoryDTO);
        
        try {
            adminNoticeService.createNotice(noticeDTO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "공지사항 등록 완료");
        }
        return "redirect:/admin/notice";
    }

    @GetMapping("/delete")
    public String deleteNotice(@RequestParam("no") int noticeNo, RedirectAttributes redirectAttributes) {
        try {
            adminNoticeService.deleteNotice(noticeNo);
            redirectAttributes.addFlashAttribute("msg", "공지사항 삭제 완료");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "공지사항 삭제 실패");
        }
        return "redirect:/admin/notice";
    }

    @GetMapping("/update")
    public String showUpdateNotice(@RequestParam("no") int noticeNo, Model model) {
        try {
            ArrayList<NoticeCategoryDTO> categories = noticeService.getCategoryList();
            NoticeDTO noticeDTO = adminNoticeService.getNoticeByNoticeNo(noticeNo);

            model.addAttribute("categories", categories);
            model.addAttribute("notice", noticeDTO);
        } catch (Exception e) {
            model.addAttribute("msg", "공지사항 조회 실패");
        }

        return "/admin/notice/UpdateNoticeForm";
    }

    @PostMapping("/update")
    public String UpdateNotice(NoticeDTO noticeDTO, @RequestParam("categoryNo") int cetegoryNo, RedirectAttributes redirectAttributes) {
        NoticeCategoryDTO noticeCategoryDTO = new NoticeCategoryDTO();
        noticeCategoryDTO.setCategoryNo(cetegoryNo);

        noticeDTO.setCategory(noticeCategoryDTO);

        try {
            adminNoticeService.updateNotice(noticeDTO);
            redirectAttributes.addFlashAttribute("msg", "공지사항 업데이트 완료");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "공지사항 업데이트 실패");
        }

        return "redirect:/admin/notice";
    }
}
