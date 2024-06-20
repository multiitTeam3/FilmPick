package com.multi.mini.member.model.dto;

import lombok.Data;

import java.sql.Date;


@Data
public class MemberDTO {
    private int memberNo;
    private String id;
    private String pw;
    private String userName;
    private String tel;
    private String role;
    private String img;
    private Date createDate;
    private Date modfiyDate;
}