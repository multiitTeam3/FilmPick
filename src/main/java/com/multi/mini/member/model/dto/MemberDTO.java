package com.multi.mini.member.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MemberDTO {
    private int rowNum;
    private int memberNo;
    private String email;
    private String password;
    private String tempPassword;
    private Boolean tempPasswordIsUse;
    private Timestamp tempExpDate;
    private String userName;
    private String tel;
    private List<RoleDTO> roles;
    private int point;
    private String address;
    private Timestamp createDate;
    private Timestamp modifyDate;
}