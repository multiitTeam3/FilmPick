package com.multi.mini.common.controller;

import com.multi.mini.common.model.dto.EmailDTO;
import com.multi.mini.common.service.AuthService;
import com.multi.mini.common.service.TempPasswordService;
import com.multi.mini.member.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "/common/findPassword";
    }


    @Transactional
    @PostMapping("/password")
    public String sendEmail(@RequestParam("memberEmail") String memberEmail, RedirectAttributes redirectAttributes){
        try {
            EmailDTO emailDTO = new EmailDTO();
            emailDTO.setReceiveAddress(memberEmail);

            tempPasswordService.sendTempPassword(emailDTO);

            redirectAttributes.addFlashAttribute("msg", "Email로 임시 비밀번호 발급 완료");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "임시 비밀번호 발급 실패");
            return "redirect:/member/login";
        }

        return "redirect:/member/login";
    }
}
