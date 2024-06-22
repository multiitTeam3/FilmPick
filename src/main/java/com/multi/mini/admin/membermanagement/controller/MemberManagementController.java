package com.multi.mini.admin.membermanagement.controller;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.common.service.PageService;
import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/admin/membermanagement")
@RequiredArgsConstructor
@Controller
public class MemberManagementController {
    private final MemberService memberService;
    private final PageService pageService;

    @GetMapping
    public String showwMemberManagement(PageDTO page, Model model) {
        page.setPage(1);
        page.setStartEnd(page.getPage());

        try {
            int count = pageService.selectMemberCount();
            int pages = count / 10 + 1;
            List<MemberDTO> list = memberService.findMemberAll(page);

            model.addAttribute("count", count);
            model.addAttribute("pages", pages);
            model.addAttribute("users", list);
        } catch (Exception e) {
            model.addAttribute("msg", "회원 리스트 조회 실패");
        }

        return "admin/membermanagement/viewUser";
    }


}
