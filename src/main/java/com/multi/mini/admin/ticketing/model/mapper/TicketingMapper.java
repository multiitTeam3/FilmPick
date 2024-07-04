package com.multi.mini.admin.ticketing.model.mapper;

import com.multi.mini.admin.ticketing.model.dto.TicketingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface TicketingMapper {
    ArrayList<TicketingDTO> findTicketingAll(@Param("type") String type, @Param("keyword") String keyword, @Param("start") int start, @Param("end") int end);

    TicketingDTO findTicketingByRsvNo(int revNo);
}
