package com.multi.mini.admin.coupon.model.dto;

import com.multi.mini.member.model.dto.MemberDTO;
import lombok.Data;

import java.sql.Date;

@Data
public class CouponDTO {
    private int rowNum;
    private int couponNo;
    private MemberDTO member;
    private String couponCode;
    private String description;
    private int discount;
    private Date useDate;
    private Date createDate;
    private Date expDate;
}
