package com.multi.mini.member.controller;

import com.multi.mini.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/profile")
@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public String showMyPage(Model model) {
        try {

        } catch (Exception e) {

        }

        return "";
    }
}
