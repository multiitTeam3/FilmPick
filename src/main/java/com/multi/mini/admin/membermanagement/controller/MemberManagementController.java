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

@RequestMapping("/admin/membermanage")
@RequiredArgsConstructor
@Controller
public class MemberManagementController {
    private final MemberService memberService;
    private final PageService pageService;

    @GetMapping
    public String showListMember(@RequestParam(required = false, name = "type") String type,
                                 @RequestParam(required = false, name = "keyword") String keyword,
                                 @RequestParam(required = false, defaultValue = "1", name = "page") int page,
                                 Model model) throws Exception{
        PageDTO pageDTO = new PageDTO();
        pageDTO.setStartEnd(page);

        try {
            // 페이지 수 계산
            int memberCount = pageService.selectMemberCount(type, keyword);
            int pages = (int) Math.ceil((double) memberCount / 10);

            List<MemberDTO> users = memberService.findMemberAll(type, keyword, pageDTO);

            model.addAttribute("count", memberCount);
            model.addAttribute("pages", pages);
            model.addAttribute("users", users);;
        } catch (Exception e) {
            model.addAttribute("msg", "회원 리스트 조회 실패");
        }

        return "admin/membermanage/listUser";
    }

//    @GetMapping("/paging")
//    public String showMemberPaging(PageDTO page, Model model) throws Exception{
//        page.setStartEnd(page.getPage());
//
//        try {
//            int count = pageService.selectMemberCount(type, keyword);
//            int pages = count / 10 + 1;
//            List<MemberDTO> list = memberService.findMemberAll(page);
//
//            model.addAttribute("count", count);
//            model.addAttribute("pages", pages);
//            model.addAttribute("users", list);
//            System.out.println(list);
//        } catch (Exception e) {
//            model.addAttribute("msg", "회원 리스트 조회 실패");
//        }
//        return "admin/membermanage/listUser2";
//    }

//    @GetMapping("/search")
//    public String findListMember(PageDTO page, Model model) throws Exception{
//        page.setPage(1);
//        page.setStartEnd(page.getPage());
//
//        try {
//            int count = pageService.selectMemberCount(type, keyword);
//            int pages = count / 10 + 1;
//            List<MemberDTO> list = memberService.findMemberAll(page);
//
//            model.addAttribute("count", count);
//            model.addAttribute("pages", pages);
//            model.addAttribute("users", list);
//            System.out.println(list);
//        } catch (Exception e) {
//            model.addAttribute("msg", "회원 리스트 조회 실패");
//        }
//
//        return "admin/membermanage/listUser";
//    }



    @GetMapping("/find")
    public String showFindMember(@RequestParam("no") int no, Model model) throws Exception{
        try {
            MemberDTO userData = memberService.findMemberByNo(no);
            model.addAttribute("user", userData);
        } catch (Exception e) {
            model.addAttribute("msg", "회원 조회 실패");
        }
        return "admin/membermanage/viewUser";
    }

    @GetMapping(value = {"/delete", "/find/delete"})
    public String deleteMember(@RequestParam("no") int no, Model model) throws Exception {
        try {
            memberService.deleteMember(no);
            model.addAttribute("msg", "회원 삭제 성공");
        } catch (Exception e) {
            model.addAttribute("msg", "회원 삭제 실패");
        }
        return  "redirect:/admin/membermanage";
    }


    @PostMapping("/find/update")
    public String updateMembers(@ModelAttribute MemberDTO userData, @RequestParam("roleList") String[] roles, Model model) throws Exception{
        System.out.println("유저 권한 확인 : " + Arrays.toString(roles));
        System.out.println("유저 DTO 확인 : " + userData);

        memberService.updateMember(userData, roles);


        return "redirect:/admin/membermanage/find?no=" + userData.getMemberNo();
    }

}
