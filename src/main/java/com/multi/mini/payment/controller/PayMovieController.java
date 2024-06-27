package com.multi.mini.payment.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.multi.mini.movie.model.dto.ReservationDataDTO;
import com.multi.mini.movie.model.dto.VWResDataDTO;
import com.multi.mini.payment.model.dto.VwGetResDataDTO;
import com.multi.mini.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PayMovieController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping("/movie/reservationseat/payment")
    public String payment(@RequestParam("list") String list, Model model,
                          @RequestParam("adult") int adult,
                          @RequestParam("teen") int teen ) {
        List<Integer> rsvNoList = new Gson().fromJson(list, new TypeToken<List<Integer>>() {
        }.getType());



        List<VwGetResDataDTO> reservations = new ArrayList<>();
        //시트이름 배열로담기
        List<String> seatNames  = new ArrayList<>();

        ArrayList<Integer> priceList = new ArrayList<>();

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

            System.out.println("================================================");




            model.addAttribute("seatNames", seatNames);
            model.addAttribute("reservations", reservations);
            model.addAttribute("adultCount", adult);
            model.addAttribute("teenCount", teen);
            model.addAttribute("totalPrice", totalPrice);

        }
        return "payment/payment";

    }



}
