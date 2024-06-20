package com.multi.mini.admin.usermgt.controller;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.common.service.PageService;
import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user")
@SessionAttributes("msg")
public class UserMgtController {
    private final MemberService memberService;
    private final PageService pageService;

    @GetMapping
    public String listUsers(PageDTO page, Model model) {
        page.setPage(1);
        page.setStartEnd(page.getPage());
        try {
            int count = pageService.selectMemberCount();
            int pages = count / 10 + 1;
            List<MemberDTO> list = memberService.selectAll(page);

            model.addAttribute("count", count);
            model.addAttribute("pages", pages);
            model.addAttribute("users", list);
        } catch (Exception e) {
            model.addAttribute("msg", "회원 리스트 조회 실패");
        }
        return "admin/usermgt/listUser";
    }

    @GetMapping("/paging")
    public String listUsers2(PageDTO page, Model model) {
        page.setStartEnd(page.getPage());
        try {
            int count = pageService.selectMemberCount();
            int pages = count / 10 + 1;
            List<MemberDTO> list = memberService.selectAll(page);

            model.addAttribute("count", count);
            model.addAttribute("pages", pages);
            model.addAttribute("list", list);
        } catch (Exception e) {
            model.addAttribute("msg", "회원 리스트 조회 실패");
        }
        return "admin/usermgt/listUser2";
    }

    @GetMapping("/view")
    public String findUser(@RequestParam("no") int no, Model model) throws Exception{
        try {
            MemberDTO userData = memberService.findUserByNo(no);
            model.addAttribute("user", userData);
        } catch (Exception e) {
            model.addAttribute("msg", "회원 리스트 조회 실패");
        }
        return "admin/usermgt/viewUser";
    }

    @PostMapping("/update")
    public String updateMemberRole(MemberDTO userData, Model model) throws Exception {
        System.out.println("DTO 데이터 확인 "+userData);

        try {
            memberService.updateMemberRole(userData);
            model.addAttribute("msg", "회원 권한 수정 성공");
        } catch (Exception e) {
            model.addAttribute("msg", "회원 권한 수정 실패");
        }

        return "redirect:/admin/user/view?no=" + userData.getMemberNo();
    }

    @GetMapping("/delete")
    public String deleteMember(@RequestParam("no") int no, Model model) throws Exception {
        try {
            memberService.deleteMember(no);
            model.addAttribute("msg", "회원 삭제 성공");
        } catch (Exception e) {
            model.addAttribute("msg", "회원 삭제 실패");
        }
        return "redirect:/admin/user";
    }
}
