package com.multi.mini.kakaopayment.controller;

import com.multi.mini.kakaopayment.service.KakaoPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class KakaoController {

    @Autowired
    private KakaoPayService kakaoPayService;

    @GetMapping("/kakaoPay")
    public String kakaopayp(){
        return "payment/kakaoPay";
    }

    @PostMapping("/kakaoPay")
    public String kakaoPay() {
        String redirectUrl = kakaoPayService.kakaoPayReady();
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model) {

    }

    @GetMapping("/kakaoPayFail")
    public String kakaoPayFail() {
        // 실패 처리 로직 추가
        return "payment/kakaoPayFail"; // 실패 페이지로 이동
    }

    @GetMapping("/kakaoPayCancel")
    public String kakaoPayCancel(Model model) {
        // 취소 처리 로직
        return "payment/kakaoPayCancel";
    }


}

