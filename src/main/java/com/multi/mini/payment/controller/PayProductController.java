package com.multi.mini.payment.controller;

import com.multi.mini.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PayProductController {


    @Autowired
    private PaymentService paymentService;



}
