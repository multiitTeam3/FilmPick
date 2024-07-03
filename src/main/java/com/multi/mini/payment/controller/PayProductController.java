package com.multi.mini.payment.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.payment.model.dto.PayProductDTO;
import com.multi.mini.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PayProductController {


    @Autowired
    private PaymentService paymentService;



    @GetMapping("/payment/paymentProduct")
    public String productPay(@RequestParam("list") String list, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        List<Integer> payNoList = new Gson().fromJson(list, new TypeToken<List<Integer>>(){

                }.getType());



        PayProductDTO payProductDTO = new PayProductDTO();

        List<PayProductDTO> productImg = new ArrayList<>();



        // 유저정보
        userDetails.getMemberNo();
        userDetails.getUsername();











return "/payment/paymentProduct";




    }

}
