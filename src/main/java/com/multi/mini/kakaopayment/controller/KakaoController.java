package com.multi.mini.kakaopayment.controller;

import com.multi.mini.kakaopayment.service.KakaoPayService;
import com.multi.mini.payment.model.dto.VwGetResDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class KakaoController {

    @Autowired
    private KakaoPayService kakaoPayService;

    @PostMapping("/kakaoPay")
    public ResponseEntity<String> kakaoPay(@RequestBody List<VwGetResDataDTO> reservations, Model model) {
        try {
            String redirectUrl = kakaoPayService.kakaoPayReady(reservations);
            return ResponseEntity.ok().body("{\"next_redirect_pc_url\": \"" + redirectUrl + "\"}");

        }catch (Exception e){

            model.addAttribute("error","결제 준비 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"결제 준비 중 오류가 발생했습니다.\"}");
        }




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

