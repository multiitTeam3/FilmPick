package com.multi.mini.admin.ticketing.controller;

import com.multi.mini.admin.ticketing.model.dto.TicketingDTO;
import com.multi.mini.admin.ticketing.service.TicketingService;
import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.common.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/ticketing")
@Slf4j
public class TicketingController {
    private final PageService pageService;
    private final TicketingService ticketingService;

    @GetMapping
    public String showTicketingList(@RequestParam(required = false, name = "type") String type,
                                   @RequestParam(required = false, name = "keyword") String keyword,
                                   @RequestParam(required = false, defaultValue = "1", name = "page") int page,
                                   Model model) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setStartEnd(page);

        try {
            // 페이지 수 계산
            log.debug("log debug = {}", "예매 전체 리스트 카운트 조회 시작");
            int ticketingCount = pageService.selectTicketingCount(type, keyword);
            int pages = (int) Math.ceil((double) ticketingCount / 10);

            log.debug("log debug = {}", "예매 전체 리스트 조회 시작");
            ArrayList<TicketingDTO> tickets = ticketingService.getTicketingList(type, keyword, pageDTO);

            model.addAttribute("tickets", tickets);

            // 페이징을 위한 파라미터
            model.addAttribute("type", type);
            model.addAttribute("keyword", keyword);
            model.addAttribute("pages", pages);
            model.addAttribute("count", ticketingCount);

        } catch (Exception e) {
            log.error("log error = ", e);
            model.addAttribute("msg", "예약 목록 조회 실패");
        }

        return "admin/ticketing/list";
    }
}
