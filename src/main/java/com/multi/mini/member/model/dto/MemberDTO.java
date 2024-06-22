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

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModfiyDate() {
        return modfiyDate;
    }

    public void setModfiyDate(Date modfiyDate) {
        this.modfiyDate = modfiyDate;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memberNo=" + memberNo +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", userName='" + userName + '\'' +
                ", tel='" + tel + '\'' +
                ", role='" + role + '\'' +
                ", img='" + img + '\'' +
                ", createDate=" + createDate +
                ", modfiyDate=" + modfiyDate +
                '}';
    }
}