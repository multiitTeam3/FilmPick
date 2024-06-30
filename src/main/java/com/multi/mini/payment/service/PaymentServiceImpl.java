package com.multi.mini.payment.service;

import com.multi.mini.payment.model.dto.PaymentsDTO;
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

	@Override
	public int updateReservationIsPaid(int rsvNo) throws Exception {
		return paymentMapper.updateReservationIsPaid(rsvNo);
	}

	@Override
	public int insertPayment(PaymentsDTO paymentsDTO) throws Exception {
		return paymentMapper.insertPayment(paymentsDTO);
	}

}

