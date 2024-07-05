package com.multi.mini.qna.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QnaDTO {
    private int qna_no;
    private int rowNum;
    private int category;
    private int member_no;
    private String memberEmail;
    private String title;
    private String content;
    private String img;
    private String isAnswer;
    private Date create_date;
    private Date modify_date;
    private String writer;

    public int getQna_no() {
        return qna_no;
    }

    public void setQna_no(int qna_no) {
        this.qna_no = qna_no;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getMember_no() {
        return member_no;
    }

    public void setMember_no(int member_no) {
        this.member_no = member_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getModify_date() {
        return modify_date;
    }

    public void setModify_date(Date modify_date) {
        this.modify_date = modify_date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "QnaDTO{" +
                "qna_no=" + qna_no +
                ", category=" + category +
                ", member_no=" + member_no +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", create_date=" + create_date +
                ", modify_date=" + modify_date +
                '}';
    }
}
