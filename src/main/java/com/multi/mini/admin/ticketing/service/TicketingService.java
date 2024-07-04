package com.multi.mini.admin.ticketing.service;

import com.multi.mini.admin.ticketing.model.dto.TicketingDTO;
import com.multi.mini.common.model.dto.PageDTO;

import java.util.ArrayList;

public interface TicketingService {
    ArrayList<TicketingDTO> getTicketingList(String type, String keyword, PageDTO pageDTO);
}
