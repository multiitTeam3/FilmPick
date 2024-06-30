package com.multi.mini.payment.controller;

import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.movie.model.dto.ReservationDTO;
import com.multi.mini.payment.model.dto.PayMovieDTO;
import com.multi.mini.payment.model.dto.PaymentsDTO;
import com.multi.mini.payment.model.dto.ReadyRequest;
import com.multi.mini.payment.service.KakaoPayService;
import com.multi.mini.payment.model.dto.KakaoReadyDTO;
import com.multi.mini.payment.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/payment")
public class KakaoController {

    private static final Logger log = LoggerFactory.getLogger(KakaoController.class);
    @Autowired
    private KakaoPayService kakaoPayService;

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/kakaoPay")
    public String kakaoPay(@ModelAttribute KakaoReadyDTO kakaoReadyDTO) throws Exception {


        //주문코드 생성
        String partnerOrderId = UUID.randomUUID().toString();
        kakaoReadyDTO.setPartnerOrderId(partnerOrderId);





        System.out.println("결제준비 데이터확인: "+kakaoReadyDTO);

        String redirectUrl = kakaoPayService.kakaoPayReady(kakaoReadyDTO);

        return "redirect:" + redirectUrl;
    }


    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token")String pg_token,
                                  HttpSession session
                                  ) throws Exception {


        KakaoReadyDTO kakaoReadyDTO = (KakaoReadyDTO) session.getAttribute("kakaoReadyDTO");
        int Discount = 1000;


        PaymentsDTO paymentsDTO = new PaymentsDTO();
        paymentsDTO.setMemberNo(kakaoReadyDTO.getMemberNo());
        paymentsDTO.setPayMethod("kakao");
        paymentsDTO.setOrdCode(kakaoReadyDTO.getPartnerOrderId());
        paymentsDTO.setAmount(kakaoReadyDTO.getTotalPrice());
        paymentsDTO.setDiscount(1000);
        paymentsDTO.setTotalPrice(kakaoReadyDTO.getTotalPrice());
        paymentService.insertPayment(paymentsDTO);
        System.out.println(pg_token);

        System.out.println("gg :"+kakaoReadyDTO);
        System.out.println("================"+ paymentsDTO);


        paymentService.insertPayment(paymentsDTO);

        PayMovieDTO payMovieDTO = new PayMovieDTO();
        ReservationDTO reservationDTO = new ReservationDTO();


        payMovieDTO.setPayNo(payMovieDTO.getPayNo());
        payMovieDTO.setRsvNo(reservationDTO.getRsvNo());
        payMovieDTO.setMovieTitle(kakaoReadyDTO.getMovieTitle());
        payMovieDTO.setTicketQuantity(kakaoReadyDTO.getTotalPrice());




        return  "redirect:/payment/kakaoPaySuccess";
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

