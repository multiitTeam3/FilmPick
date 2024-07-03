package com.multi.mini.admin.ticketing.service;

import com.multi.mini.admin.ticketing.model.dto.TicketingDTO;
import com.multi.mini.admin.ticketing.model.mapper.TicketingMapper;
import com.multi.mini.common.model.dto.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TicketingServiceImpl implements TicketingService{
    private final TicketingMapper ticketingMapper;


    @Override
    public ArrayList<TicketingDTO> getTicketingList(String type, String keyword, PageDTO pageDTO) {
        ArrayList<TicketingDTO> tickets = ticketingMapper.findTicketingAll(type, keyword, pageDTO.getStart(), pageDTO.getEnd());
        if(tickets == null) throw new RuntimeException("예매 전체 목록 DB 조회에 실패했습니다");
        return tickets;
    }
}
