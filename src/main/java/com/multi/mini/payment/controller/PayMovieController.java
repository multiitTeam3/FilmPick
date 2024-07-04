package com.multi.mini.payment.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.multi.mini.admin.coupon.model.dto.CouponDTO;
import com.multi.mini.admin.coupon.service.CouponService;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.payment.model.dto.KakaoReadyDTO;
import com.multi.mini.payment.model.dto.VwGetResDataDTO;
import com.multi.mini.payment.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PayMovieController {

    @Autowired
    private PaymentService paymentService;

    private CouponService couponService;



    @GetMapping("/movie/reservationseat/payment")
    public String payment(@RequestParam("list") String list, Model model,
                          @RequestParam("adult") int adult,
                          @RequestParam("teen") int teen, HttpSession session) throws Exception {


        //역직렬화 GSON JSON ob > java ob 로 변경
        List<Integer> rsvNoList = new Gson().fromJson(list, new TypeToken<List<Integer>>() {
        }.getType());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

//        // 단일 예약 번호를 리스트로 변환
//        List<Integer> rsvNoList = Arrays.asList(rsvNos);
        //VwGetResDataDTO 객체생성
        List<VwGetResDataDTO> reservations = new ArrayList<>();
        //seatNames 배열담기
        List<String> seatNames = new ArrayList<>();
        //가격을 담기위한 리스트 생성
        ArrayList<Integer> priceList = new ArrayList<>();
        //로그인된 아이디가져오기
        String username = userDetails.getUsername();
        int memberNo = userDetails.getMemberNo();



        if (adult > 0) {

            for (int i = 0; i < adult; i++) {
                priceList.add(12000);
            }
        }

        if (teen > 0) {

            for (int i = 0; i < teen; i++) {
                priceList.add(8000);
            }
        }
        int totalPrice = 0;
        for(int i=0; i < priceList.size(); i++){

            totalPrice += priceList.get(i);
        }

        System.out.println("totalPrice: "+ totalPrice);


        for (int rsvNo : rsvNoList) {
            try {
                VwGetResDataDTO reservation = paymentService.getResNo(rsvNo);
                reservations.add(reservation);


                // seatName을 콤마로 분할하여 seatNames 리스트에 추가
                if (reservation.getSeatName() != null && !reservation.getSeatName().isEmpty()) {
                    seatNames.addAll(Arrays.asList(reservation.getSeatName().split(",")));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            //확인을 위해 프린트
            System.out.println("================================================");

            System.out.println("reservations: "+ reservations);
            System.out.println("seatNames: "+ seatNames);
            System.out.println("adult: "+ adult);
            System.out.println("teen: "+ teen);
            System.out.println("priceList: "+ priceList);
            System.out.println("rsvNos: "+ list);

            System.out.println("================================================");

            List<CouponDTO> coupons = paymentService.getCouponsByMemberNo(memberNo);



            System.out.println("coupons: "+ coupons);
            KakaoReadyDTO kakaoReadyDTO = new KakaoReadyDTO();
            kakaoReadyDTO.setRsvNoList(new ArrayList<>(rsvNoList));//예약번호 리스트
            kakaoReadyDTO.setRsv(reservations.get(0)); //
            kakaoReadyDTO.setMovieTitle(reservations.get(0).getMovieTitle());
            kakaoReadyDTO.setUsername(username);
            kakaoReadyDTO.setTotalPrice(totalPrice);
            kakaoReadyDTO.setAdultCount(adult);
            kakaoReadyDTO.setTeenCount(teen);
            kakaoReadyDTO.setMemberNo(memberNo);


            System.out.println(kakaoReadyDTO);




            model.addAttribute("memberNo",memberNo);
            model.addAttribute("username",username);
            model.addAttribute("seatNames", seatNames);
            model.addAttribute("reservations", reservations); //
            model.addAttribute("adultCount", adult); //성인 수
            model.addAttribute("teenCount", teen); //청소년 수
            model.addAttribute("totalPrice", totalPrice); //총합가격
            model.addAttribute("rsvNoList", list); //예약번호
            model.addAttribute("kakaoReadyDTO", kakaoReadyDTO);
            model.addAttribute("coupons", coupons);

            session.setAttribute("reservations", reservations);

        }
        return "payment/payment";

    }



}
