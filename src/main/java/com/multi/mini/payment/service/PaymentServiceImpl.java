package com.multi.mini.payment.service;

import com.multi.mini.payment.model.dto.VwGetResDataDTO;
import com.multi.mini.payment.model.mapper.PaymentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;




    @Override
	public VwGetResDataDTO getResNo(int rsvNo) throws Exception {
		return paymentMapper.vwResDataNo(rsvNo);
	}

}

