package com.multi.mini.payment.service;


import com.multi.mini.payment.model.dto.PaymentsDTO;
import com.multi.mini.payment.model.dto.VwGetResDataDTO;

public interface PaymentService {


	VwGetResDataDTO getResNo(int rsvNo) throws Exception;

	int updateReservationIsPaid(int rsvNo) throws Exception;

	int insertPayment(PaymentsDTO paymentsDTO) throws Exception;
}
