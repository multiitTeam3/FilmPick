package com.multi.mini.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Customer/Index")
@RequiredArgsConstructor
@Controller
public class CustomerController {

    @GetMapping
    public String showCustomer() {
        return "common/customer/customer";
    }
}
