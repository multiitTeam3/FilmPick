package com.multi.mini.admin.coupon.model.mapper;

import com.multi.mini.admin.coupon.model.dto.CouponDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CouponMapper {

    @Select("SELECT coupon_no FROM mem_coupon WHERE coupon_code = #{ couponCode }")
    CouponDTO findCouponByCode(String couponCode);

    int insetCoupon(CouponDTO couponCode);

    List<CouponDTO> findCouponAll();
}
