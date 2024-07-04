package com.multi.mini.payment.service;


import com.multi.mini.admin.coupon.model.dto.CouponDTO;
import com.multi.mini.payment.model.dto.*;

import java.util.List;

public interface PaymentService {


	VwGetResDataDTO getResNo(int rsvNo) throws Exception;

	void markReservationAsPaid(List<Integer> rsvNoList) throws Exception;

	int insertPayment(PaymentsDTO paymentsDTO) throws Exception;


	List<CouponDTO> getCouponsByMemberNo(int memberNo) throws Exception;

	void useCoupon(int memberNo, String couponCode) throws Exception;

	int deletePaymentByReservation(int rsvNO) throws Exception;

	int insertPaymentMovie(PayMovieDTO payMovieDTO) throws Exception;

	Integer selectPaymentNo() throws Exception;


	PayProductDTO selectProductByPay(int productNo) throws Exception;

	void insertPaymentProduct(PayProductAndPaymentDTO paymentProduct) throws Exception;
}
