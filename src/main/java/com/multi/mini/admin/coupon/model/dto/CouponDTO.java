package com.multi.mini.admin.coupon.model.dto;

import com.multi.mini.member.model.dto.MemberDTO;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class CouponDTO {
    private int rowNum;
    private int couponNo;
    private MemberDTO member;
    private String couponCode;
    private String description;
    private int discount;
    private Date useDate;
    private LocalDate createDate;
    private Date expDate;
}
