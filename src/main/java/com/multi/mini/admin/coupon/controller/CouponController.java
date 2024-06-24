package com.multi.mini.admin.coupon.controller;

import com.multi.mini.admin.coupon.model.dto.CouponDTO;
import com.multi.mini.admin.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/admin/coupon")
@RequiredArgsConstructor
@Controller
public class CouponController {
    private final CouponService couponService;

    @GetMapping
    public String showListCoupon(Model model) {
        try {
            List<CouponDTO>coupons = couponService.findCouponAll();
            model.addAttribute("coupons", coupons);
        } catch (Exception e) {
            model.addAttribute("msg", "리스트 조회 실패");
        }
        return "/admin/coupon/viewCoupon";
    }

    @PostMapping("/insert")
    public String insertCoupon(@RequestParam("email") String email, @RequestParam("discount") int discount,
                               @RequestParam("desc") String desc, @RequestParam("expDate") int expDate, Model model , RedirectAttributes redirectAttributes) throws Exception{
        CouponDTO couponDTO = new CouponDTO();

        try {
            couponService.insertCoupon(couponDTO, email, discount, desc, expDate);
            redirectAttributes.addFlashAttribute("msg", "쿠폰 등록 완료");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "쿠폰 등록 실패");
        }

        return "redirect:/admin/coupon";
    }

    @GetMapping("/delete")
    public String deleteCoupn(@RequestParam("code") String couponCode, RedirectAttributes redirectAttributes, Model model) {
            try {
                couponService.deleteCoupon(couponCode);
                redirectAttributes.addFlashAttribute("msg", "쿠폰 삭제 성공");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("msg", "쿠폰 삭제 실패");
            }
        return "redirect:/admin/coupon";
    }
}
