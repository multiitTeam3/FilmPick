package com.multi.mini.customer.notice.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class NoticeDTO {
    private int rowNum;
    private int noticeNo;
    private int memberNo;
    private String memberEmail;
    private NoticeCategoryDTO category;
    private String title;
    private String content;
    private Date createDate;
}
