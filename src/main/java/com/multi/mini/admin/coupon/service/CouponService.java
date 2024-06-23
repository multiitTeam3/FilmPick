package com.multi.mini.admin.coupon.service;

import com.multi.mini.admin.coupon.model.dto.CouponDTO;

import java.util.List;

public interface CouponService {

    void insertCoupon(CouponDTO couponDTO, String email, int discount, String desc, int expDate) throws Exception;

    List<CouponDTO> findCouponAll() throws Exception;

    void deleteCoupon(String couponCode) throws Exception;
}
