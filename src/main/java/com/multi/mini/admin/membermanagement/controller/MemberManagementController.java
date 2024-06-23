package com.multi.mini.admin.membermanagement.controller;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.common.service.PageService;
import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/admin/membermanagement")
@RequiredArgsConstructor
@Controller
public class MemberManagementController {
    private final MemberService memberService;
    private final PageService pageService;

    @GetMapping
    public String showListMember(PageDTO page, Model model) throws Exception{
        page.setPage(1);
        page.setStartEnd(page.getPage());

        try {
            int count = pageService.selectMemberCount();
            int pages = count / 10 + 1;
            List<MemberDTO> list = memberService.findMemberAll(page);

            model.addAttribute("count", count);
            model.addAttribute("pages", pages);
            model.addAttribute("users", list);
            System.out.println(list);
        } catch (Exception e) {
            model.addAttribute("msg", "회원 리스트 조회 실패");
        }

        return "admin/membermanagement/listUser";
    }

    @GetMapping("/paging")
    public String showMemberPaging(PageDTO page, Model model) throws Exception{
        page.setStartEnd(page.getPage());

        try {
            int count = pageService.selectMemberCount();
            int pages = count / 10 + 1;
            List<MemberDTO> list = memberService.findMemberAll(page);

            model.addAttribute("count", count);
            model.addAttribute("pages", pages);
            model.addAttribute("users", list);
            System.out.println(list);
        } catch (Exception e) {
            model.addAttribute("msg", "회원 리스트 조회 실패");
        }
        return "admin/membermanagement/listUser2";
    }

    @GetMapping
    public String findListMember(PageDTO page, Model model) throws Exception{
        page.setPage(1);
        page.setStartEnd(page.getPage());

        try {
            int count = pageService.selectMemberCount();
            int pages = count / 10 + 1;
            List<MemberDTO> list = memberService.findMemberAll(page);

            model.addAttribute("count", count);
            model.addAttribute("pages", pages);
            model.addAttribute("users", list);
            System.out.println(list);
        } catch (Exception e) {
            model.addAttribute("msg", "회원 리스트 조회 실패");
        }

        return "admin/membermanagement/listUser";
    }



    @GetMapping("/find")
    public String showFindMember(@RequestParam("no") int no, Model model) throws Exception{
        try {
            MemberDTO userData = memberService.findMemberByNo(no);
            model.addAttribute("user", userData);
        } catch (Exception e) {
            model.addAttribute("msg", "회원 조회 실패");
        }
        return "admin/membermanagement/viewUser";
    }

    @GetMapping(value = {"/delete", "/find/delete"})
    public String deleteMember(@RequestParam("no") int no, Model model) throws Exception {
        try {
            memberService.deleteMember(no);
            model.addAttribute("msg", "회원 삭제 성공");
        } catch (Exception e) {
            model.addAttribute("msg", "회원 삭제 실패");
        }
        return  "redirect:/admin/membermanagement";
    }


    @PostMapping("/find/update")
    public String updateMembers(@ModelAttribute MemberDTO userData, @RequestParam("roleList") String[] roles, Model model) throws Exception{
//        List<RoleDTO> roleList = new ArrayList<>();
//        for (String roleName : roles) {
//            RoleDTO roleDTO = new RoleDTO();
//            roleDTO.setRoleName(roleName);
//            roleList.add(roleDTO);
//        }
//        userData.setRoles(roleList);
        System.out.println("유저 권한 확인 : " + Arrays.toString(roles));
        System.out.println("유저 DTO 확인 : " + userData);

        memberService.updateMember(userData, roles, null);


        return "redirect:/admin/membermanagement/find?no=" + userData.getMemberNo();
    }

}
