package com.multi.mini.common.controller;

import com.multi.mini.common.model.dto.EmailDTO;
import com.multi.mini.common.service.AuthService;
import com.multi.mini.common.service.TempPasswordService;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.member.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TempPasswordService tempPasswordService;

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

    @GetMapping("/password")
    public String showTempPass() {
        return "/member/findPassword";
    }


    @Transactional
    @PostMapping("/password")
    public String sendTempPasswordEmail(@RequestParam("memberEmail") String memberEmail){
        try {
            EmailDTO emailDTO = new EmailDTO();
            emailDTO.setReceiveAddress(memberEmail);

            tempPasswordService.sendTempPassword(emailDTO);
        } catch (Exception e) {
            return "redirect:/member/login";
        }

        return "redirect:/member/login";
    }

    @GetMapping("/changePassword")
    public String showChangePassword() {
        return "/member/changePassword";
    }

    @ResponseBody
    @PostMapping("/changePassword")
    public boolean changePassword(@RequestParam("memberPassword") String password) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return authService.changePassword(password, userDetails);
    }
}
