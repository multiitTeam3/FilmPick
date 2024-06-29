package com.multi.mini.member.controller;

import com.multi.mini.admin.coupon.model.dto.CouponDTO;
import com.multi.mini.admin.coupon.service.CouponService;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.member.service.MemberService;
import com.multi.mini.qna.model.dto.QnaDTO;
import com.multi.mini.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
@Controller
public class MemberController {
    private final CouponService couponService;
    private final MemberService memberService;
    private final QnaService qnaService;

    @GetMapping("/profile")
    public String showMyPage() {
        return "/member/mypage";
    }

    @GetMapping("/profile/memberInfo")
    public String showMemberInfo(Model model) {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 회원 정보를 set
        MemberDTO userData = new MemberDTO();
        userData.setMemberNo(userDetails.getMemberNo());
        userData.setEmail(userDetails.getUsername());
        userData.setUserName(userDetails.getNickName());
        userData.setAddress(userDetails.getAddress());
        userData.setTel(userDetails.getTel());
        userData.setCreateDate(Timestamp.valueOf(userDetails.getCreateDate()));

        model.addAttribute("user", userData);

        return "member/tab/memberInfo";
    }

    @GetMapping("/profile/couponsPoints")
    public String showCouponsPoints(Model model) {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 회원 정보를 set
        MemberDTO userData = new MemberDTO();
        userData.setMemberNo(userDetails.getMemberNo());
        userData.setPoint(userDetails.getPoint());

        try {
            ArrayList<CouponDTO> coupons = couponService.findCouponListByMemberNo(userData.getMemberNo());

            model.addAttribute("user", userData);
            model.addAttribute("coupons", coupons);
        } catch (Exception e) {
            log.error("error log = {}", e);
        }

        return "member/tab/couponsPoints";
    }

    @GetMapping("/profile/memberInfo/update")
    public String showUpdateInfo(Model model) {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 회원 정보를 set
        MemberDTO userData = new MemberDTO();
        userData.setMemberNo(userDetails.getMemberNo());
        userData.setEmail(userDetails.getUsername());
        userData.setUserName(userDetails.getNickName());
        userData.setAddress(userDetails.getAddress());
        userData.setTel(userDetails.getTel());
        userData.setCreateDate(Timestamp.valueOf(userDetails.getCreateDate()));

        model.addAttribute("user", userData);

        return "member/tab/updateMemberInfo";
    }

    @GetMapping("/profile/purchaseHistory")
    public String showPurchaseHistory() {
        return "member/tab/purchaseHistory";
    }

    @GetMapping("/profile/questionHistory")
    public String showQuestionHistory(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        try {
            List<QnaDTO> qnas = qnaService.selectQnaAll(userDetails.getMemberNo());
            model.addAttribute("qnas", qnas);
        } catch (Exception e) {
            model.addAttribute("msg", "문의글 조회 실패");
        }

        return "member/tab/questionHistory";
    }

    @PostMapping("/profile/memberInfo/update")
    public String updateMemberInfo(MemberDTO memberDTO, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        memberDTO.setMemberNo(userDetails.getMemberNo());
        memberDTO.setEmail(userDetails.getUsername());

        try {
            memberService.updateMember(memberDTO, null);
            
            userDetails.update(memberDTO);
            
            // 업데이트 정보가 담긴 세션을 새로 선언하여 교체
            Authentication updateAuthentication = new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(updateAuthentication);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "회원 정보 변경 실패");
        }

        return "redirect:/member/profile";
    }
}
