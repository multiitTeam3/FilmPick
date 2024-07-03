package com.multi.mini.admin.bookings.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/bookings")
public class BookingsController {


    @GetMapping
    public String showBookingsList(@RequestParam(required = false, name = "type") String type,
                                   @RequestParam(required = false, name = "keyword") String keyword,
                                   @RequestParam(required = false, defaultValue = "1", name = "page") int page,
                                   Model model) {
        try {

        } catch (Exception e) {

        }

        return "admin/bookings/list";
    }
}
