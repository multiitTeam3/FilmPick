package com.multi.mini.payment.model.mapper;

import com.multi.mini.movie.model.dto.VWResDataDTO;
import com.multi.mini.payment.model.dto.VwGetResDataDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {



    VwGetResDataDTO vwResDataNo(int rsvNo) throws Exception;


}
