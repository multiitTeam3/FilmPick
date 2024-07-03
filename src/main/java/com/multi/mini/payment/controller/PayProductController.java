package com.multi.mini.payment.controller;

import com.multi.mini.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayProductController {


    @Autowired
    private PaymentService paymentService;



    @GetMapping()
    public String productPay(){


    }

}
