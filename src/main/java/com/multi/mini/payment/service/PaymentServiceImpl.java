package com.multi.mini.payment.service;

import com.multi.mini.admin.coupon.model.dto.CouponDTO;
import com.multi.mini.admin.coupon.model.mapper.CouponMapper;
import com.multi.mini.member.model.mapper.MemberMapper;
import com.multi.mini.payment.model.dto.*;
import com.multi.mini.payment.model.mapper.PaymentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;


	private final CouponMapper couponMapper;

	private final MemberMapper memberMapper;




    @Override
	public VwGetResDataDTO getResNo(int rsvNo) throws Exception {
		return paymentMapper.vwResDataNo(rsvNo);
	}



	@Override
	public void markReservationAsPaid(List<Integer> rsvNoList) throws Exception {
		for (int rsvNo : rsvNoList){
			paymentMapper.updateReservationIsPaid(rsvNo);

		}
	}

	@Override
	public int insertPayment(PaymentsDTO paymentsDTO) throws Exception {
		return paymentMapper.insertPayment(paymentsDTO);
	}


	@Override
	public List<CouponDTO> getCouponsByMemberNo(int memberNo) {
		return couponMapper.findCouponsByMemberNo(memberNo);
	}

	@Override
	@Transactional
	public void useCoupon(int memberNo, String couponCode) {
		// 1. 쿠폰을 사용하는 로직 (예: 쿠폰 적용하여 결제 처리 등)
		// 해당 로직은 결제 서비스나 다른 관련 서비스에 작성할 수 있음

		// 2. 쿠폰 삭제
		couponMapper.deleteCouponByMemberAndCode(memberNo, couponCode);
	}




	@Override
	public int deletePaymentByReservation(int rsvNO) throws Exception {
		return paymentMapper.deletePaymentByReservation(rsvNO);
	}

	@Override
	public int insertPaymentMovie(PayMovieDTO payMovieDTO) throws Exception {
		return paymentMapper.insertPaymentMovie(payMovieDTO);
	}

	@Override
	public Integer selectPaymentNo() throws Exception {
		return paymentMapper.selectPaymentNo();
	}

	@Override
	public PayProductDTO selectProductByPay(int productNo) throws Exception {
		return paymentMapper.selectProductByPay(productNo);
	}

	@Override
	@Transactional
	public void insertPaymentProduct(PayProductAndPaymentDTO paymentProduct) throws Exception {
		paymentMapper.insertPaymentProduct(paymentProduct);
	}


}

