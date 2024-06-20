package com.multi.mini.community.model.dto;

import com.multi.mini.member.model.dto.MemberDTO;
import lombok.Data;

import java.sql.Date;

@Data
public class BoardDTO {
    private int boardNo;
    private MemberDTO writer;
    private String title;
    private String content;
    private String img;
    private String type;
    private Date createDate;
    private Date modifyDate;
}
