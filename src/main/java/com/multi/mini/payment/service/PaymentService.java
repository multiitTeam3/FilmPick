package com.multi.mini.payment.service;


import com.multi.mini.admin.coupon.model.dto.CouponDTO;
import com.multi.mini.payment.model.dto.PaymentsDTO;
import com.multi.mini.payment.model.dto.VwGetResDataDTO;

import java.util.List;

public interface PaymentService {


	VwGetResDataDTO getResNo(int rsvNo) throws Exception;

	void markReservationAsPaid(List<Integer> rsvNoList) throws Exception;

	int insertPayment(PaymentsDTO paymentsDTO) throws Exception;


	List<CouponDTO> getCouponsByMemberNo(int memberNo) throws Exception;

	void useCoupon(int memberNo, String couponCode) throws Exception;

	int deletePaymentByReservation(int rsvNO) throws Exception;
}
