package com.multi.mini.common.controller;

import com.multi.mini.common.service.AuthService;
import com.multi.mini.member.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/sign-up")
    public String createMemberForm() {
        return "common/signup";
    }

    @PostMapping("/sign-up")
    public String createMember(MemberDTO dto, Model model) {
        try {
            authService.createMember(dto);
            model.addAttribute("msg", "회원가입에 성공했습니다.");
        } catch (Exception e) {
            model.addAttribute("msg", "회원가입에 실패했습니다.");
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "common/login";
    }

    @GetMapping("/logout")
    public void memberLogOut(){}
}
