package com.multi.mini.admin.coupon.model.mapper;

import com.multi.mini.admin.coupon.model.dto.CouponDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CouponMapper {

    @Select("SELECT coupon_no FROM mem_coupon WHERE coupon_code = #{ couponCode }")
    CouponDTO findCouponByCode(String couponCode);

    int insetCoupon(CouponDTO couponCode);

    List<CouponDTO> findCouponAll(@Param("type") String type, @Param("keyword") String keyword, @Param("start") int start, @Param("end") int end);

    @Delete("DELETE FROM mem_coupon WHERE coupon_code = #{ couponCode }")
    int deleteCoupon(String couponCode);

<<<<<<< HEAD
    List<CouponDTO> findCouponListByMemberNo(int memberNo);
=======

    List<CouponDTO> findCouponsByMemberNo(int memberNo);

    // 회원의 특정 쿠폰을 삭제하는 메서드
    void deleteCouponByMemberAndCode(@Param("memberNo") int memberNo, @Param("couponCode") String couponCode);
>>>>>>> feature/payment
}
