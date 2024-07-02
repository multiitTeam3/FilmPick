package com.multi.mini.product.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductDTO {
    private int productNo;
    private String name;
    private String content;
    private int price;
    private int categorycode;
    private String productImg;
    private Timestamp createDate;
}
