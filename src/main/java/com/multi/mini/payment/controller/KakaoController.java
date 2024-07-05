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

import java.util.*;

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


    @PostMapping("/kakaoProductPay")
    public String kakaoPayProduct(@RequestParam("productNames") List<String> productNames,
                                  @RequestParam("productQuantities") List<Integer> productQuantities,
                                  @RequestParam("productNos") List<Integer> productNos,
                                  @RequestParam("totalPrice") int totalprice,
                                  @RequestParam("memberNo") int memberNo,
                                  @RequestParam("username") String username,
                                  @RequestParam("totalQuantity") int totalQuantity,
                                  HttpSession session) throws Exception {



        List<PayProductDTO> payProductDTOList = new ArrayList<>();
        for (int i = 0; i < productNames.size(); i++) {
            PayProductDTO payProductDTO = new PayProductDTO();
            payProductDTO.setProductName(productNames.get(i));
            payProductDTO.setProductQuantity(productQuantities.get(i));
            payProductDTO.setProductNo(productNos.get(i));
            payProductDTOList.add(payProductDTO);

            System.out.println(payProductDTOList);
        }

        System.out.println(payProductDTOList);


        String partnerOrderId = UUID.randomUUID().toString();

        session.setAttribute("payProductDTOList",payProductDTOList);
        session.setAttribute("totalprice",totalprice);
        session.setAttribute("memberNo",memberNo);
        session.setAttribute("username",username);
        session.setAttribute("totalQuantity",totalQuantity);
        session.setAttribute("productNames",productNames);
        session.setAttribute("productQuantities",productQuantities);


        System.out.println("11111111111111"+payProductDTOList);



        String redirectUrl = kakaoPayService.kakaoPayReadyProduct(payProductDTOList, totalprice, memberNo, username, partnerOrderId,totalQuantity);

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

        //VIEW 넘기기 위해 담아주기
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


        //db저장 결제정보
        paymentService.insertPayment(paymentsDTO);


        //db



        Integer paymentsNo = paymentService.selectPaymentNo();
        if (paymentsNo == null) {
            throw new RuntimeException("결제번호가없습니다..!");
        }



        List<Integer> rsvlist = kakaoReadyDTO.getRsvNoList();
        if (rsvlist ==null) {
            throw new RuntimeException("예매번호가없습니다..!");
        }

        System.out.println(rsvlist+"bbbbbbbbbbbb");
        System.out.println(paymentsNo+"aaaaaaaaaa");

        for(Integer rsvNo : rsvlist){
            //결제 완료 되면 예매테이블 결제정보가 N > Y 로 업데이트
            paymentService.markReservationAsPaid(Collections.singletonList(rsvNo));

            PayMovieDTO payMovieDTO = new PayMovieDTO();
            payMovieDTO.setPayNo(paymentsNo);
            payMovieDTO.setRsvNo(rsvNo);
            payMovieDTO.setMovieTitle(kakaoReadyDTO.getMovieTitle());
            payMovieDTO.setTicketQuantity(kakaoReadyDTO.getAdultCount()+kakaoReadyDTO.getTeenCount());
            //영화 테이블 저장


            paymentService.insertPaymentMovie(payMovieDTO);

        };


        System.out.println("KakaoReadyDTO: " + kakaoReadyDTO);
        System.out.println("PaymentsDTO: " + paymentsDTO);


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
    @GetMapping("/kakaoPayProductSuccess")
    public String kakaoPayProductSuccess(@RequestParam("pg_token")String pg_token, @RequestParam("partnerOrderId") String partnerOrderId,HttpSession session,Model model) throws Exception {


        List<PayProductDTO> payProductDTOList = (List<PayProductDTO>) session.getAttribute("payProductDTOList");
        int totalprice = (int) session.getAttribute("totalprice");
        int memberNo = (int) session.getAttribute("memberNo");
        String username = (String) session.getAttribute("username");
        int totalQuantity = (int) session.getAttribute("totalQuantity");

        List<String> productNames = (List<String>) session.getAttribute("productNames");

        List<Integer> productQuantities = (List<Integer>) session.getAttribute("productQuantities");




        String approveResponse = kakaoPayService.approveProduct(pg_token,partnerOrderId, username);

        PaymentsDTO paymentsDTO = new PaymentsDTO();
        paymentsDTO.setPayMethod("KAKAO");
        paymentsDTO.setOrdCode(partnerOrderId);
        paymentsDTO.setDiscount(0);
        paymentsDTO.setMemberNo(memberNo);
        paymentsDTO.setTotalPrice(totalprice - paymentsDTO.getDiscount());
        paymentsDTO.setAmount(totalprice);



        paymentService.insertPayment(paymentsDTO);


        Integer paymentsNo = paymentService.selectPaymentNo();
        if (paymentsNo == null) {
            throw new RuntimeException("결제번호가없습니다..!");
        }

        //pay_product_and_payment 테이블에는 pap_product_no 만큼 추가 20,21,22 번 상품 결제시 상품번호 db에 하나씩 넣어주기
//    for(PayProductDTO payProductDTO : payProductDTOList){
//        int productNo = payProductDTO.getProductNo();
//
//        PayProductAndPaymentDTO paymentProduct = new PayProductAndPaymentDTO();
//
//        paymentProduct.setPapProductNo(productNo);
//        paymentProduct.setProductName(productName);
//        paymentProduct.setProductQuantity(productQuantities);
//        paymentProduct.setPapPayNo(paymentsNo);
//
//       paymentService.insertPaymentProduct(productNo);
//    }


        for (int i = 0; i < payProductDTOList.size(); i++){
            PayProductDTO payProductDTO = payProductDTOList.get(i);
            int productNo = payProductDTO.getProductNo();

            PayProductAndPaymentDTO paymentProduct = new PayProductAndPaymentDTO();
            paymentProduct.setPapProductNo(productNo);
            paymentProduct.setProductName(productNames.get(i));
            paymentProduct.setProductQuantity(productQuantities.get(i));
            paymentProduct.setPapPayNo(paymentsNo);

            paymentService.insertPaymentProduct(paymentProduct);


        }
        // 세션 데이터 삭제
        session.removeAttribute("payProductDTOList");
        session.removeAttribute("totalprice");
        session.removeAttribute("memberNo");
        session.removeAttribute("username");
        session.removeAttribute("totalQuantity");

        model.addAttribute("payProductDTOList", payProductDTOList);
        model.addAttribute("paymentsDTO", paymentsDTO);

        return "payment/kakaoPayProductSuccess";
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