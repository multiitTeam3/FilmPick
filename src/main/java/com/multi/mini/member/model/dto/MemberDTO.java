package com.multi.mini.member.model.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;


@Data
public class MemberDTO {
    private int rowNum;
    private int memberNo;
    private String email;
    private String password;
    private String userName;
    private String tel;
    private List<RoleDTO> roles;
    private int point;
    private String address;
    private Date createDate;
    private Date modfiyDate;
}