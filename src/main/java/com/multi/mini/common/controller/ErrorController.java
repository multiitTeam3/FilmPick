package com.multi.mini.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/denied")
    public String accessDenied(){
        return "error/denied";
    }
}
