package com.multi.mini.payment.controller;

import com.multi.mini.movie.model.dto.SeatDTO;
import com.multi.mini.payment.model.dto.*;
import com.multi.mini.payment.service.KakaoPayService;
import com.multi.mini.payment.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public String kakaoPay(@ModelAttribute KakaoReadyDTO kakaoReadyDTO, HttpSession session) throws Exception {


        //주문코드 생성
        String partnerOrderId = UUID.randomUUID().toString();
        kakaoReadyDTO.setPartnerOrderId(partnerOrderId);

        session.setAttribute("kakaoReadyDTO", kakaoReadyDTO);
        System.out.println("결제준비 데이터확인: "+kakaoReadyDTO);

        String redirectUrl = kakaoPayService.kakaoPayReady(kakaoReadyDTO);

        return "redirect:" + redirectUrl;
    }

    @Transactional
    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token")String pg_token, @RequestParam("partnerOrderId") String partnerOrderId,HttpSession session,Model model) throws Exception {
        log.info("pg_token: " + pg_token);

        //세션으로 kakaoReadyDTO데이터 받아오기
        KakaoReadyDTO kakaoReadyDTO = (KakaoReadyDTO) session.getAttribute("kakaoReadyDTO");
        //해당 예매 정보 받아오기
        VwGetResDataDTO rsv = paymentService.getResNo(kakaoReadyDTO.getRsvNoList().get(0));

        //승인 정보
        String approveResponse = kakaoPayService.approve(pg_token, kakaoReadyDTO);
        System.out.println("결제 승인 결과: " + approveResponse);
        System.out.println("============================================================================================================"+kakaoReadyDTO);

        //모달 사용을 위해 담아주기
        kakaoReadyDTO.setPosterPath(rsv.getPosterPath());
        kakaoReadyDTO.setMovieTitle(rsv.getMovieTitle());
        kakaoReadyDTO.setSeat(rsv.getSeatName());

        //시트는 배열로 받기
        String[] seatArray = rsv.getSeatName().split(",");
        List<String> seatNames = Arrays.asList(seatArray);


        //결제테이블 DB 을 위해 정보담기
        PaymentsDTO paymentsDTO = new PaymentsDTO();
        paymentsDTO.setPayMethod(kakaoReadyDTO.getPayMethod());
        paymentsDTO.setOrdCode(partnerOrderId);
        paymentsDTO.setDiscount(1000); //할인률
        paymentsDTO.setMemberNo(kakaoReadyDTO.getMemberNo());
        paymentsDTO.setTotalPrice(kakaoReadyDTO.getTotalPrice() - paymentsDTO.getDiscount()); //할인이 적용된금액
        paymentsDTO.setAmount(kakaoReadyDTO.getTotalPrice()); //상품금액

        //영화 테이블  DB 을 위해 정보담기

        PayMovieDTO payMovieDTO = new PayMovieDTO();

        System.out.println("KakaoReadyDTO: " + kakaoReadyDTO);
        System.out.println("PaymentsDTO: " + paymentsDTO);


        //db저장 결제정보
        paymentService.insertPayment(paymentsDTO);
        //결제 완료 되면 예매테이블 결제정보가 N > Y 로 업데이트
        paymentService.markReservationAsPaid(kakaoReadyDTO.getRsvNoList());
        //영화 테이블 저장


        //세션 삭제
        session.removeAttribute("kakaoReadyDTO");

        //VIEW 출력
        model.addAttribute("kakaoReadyDTO", kakaoReadyDTO);
        model.addAttribute("paymentsDTO",paymentsDTO);
        model.addAttribute("adultCount", kakaoReadyDTO.getAdultCount());
        model.addAttribute("teenCount", kakaoReadyDTO.getTeenCount());
        model.addAttribute("seatNames",seatNames);


        return "payment/kakaoPaySuccess";
    }

    @Transactional
    @GetMapping("/kakaoPayFail")
    public String kakaoPayFail(HttpSession session,Model model) throws Exception {

        KakaoReadyDTO kakaoReadyDTO = (KakaoReadyDTO) session.getAttribute("kakaoReadyDTO");
        //해당 예매 정보 받아오기
        VwGetResDataDTO rsv = paymentService.getResNo(kakaoReadyDTO.getRsvNoList().get(0));

        paymentService.deletePaymentByReservation(rsv.getRsvNo());

        // 실패 처리 로직 추가
        return "payment/kakaoPayFail"; // 실패 페이지로 이동
    }

    @Transactional
    @GetMapping("/kakaoPayCancel")
    public String kakaoPayCancel(HttpSession session,Model model) throws Exception {
        KakaoReadyDTO kakaoReadyDTO = (KakaoReadyDTO) session.getAttribute("kakaoReadyDTO");
        //해당 예매 정보 받아오기
        VwGetResDataDTO rsv = paymentService.getResNo(kakaoReadyDTO.getRsvNoList().get(0));

        paymentService.deletePaymentByReservation(rsv.getRsvNo());

        // 취소 처리 로직
        return "payment/kakaoPayCancel";
    }

}