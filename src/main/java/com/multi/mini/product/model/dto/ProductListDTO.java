package com.multi.mini.product.model.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ProductListDTO {
    private int memberNo;
    private String username;


    private ArrayList<ProductDTO> productList = new ArrayList<>();
    private ArrayList<Integer> productQuantityList = new ArrayList<>();

    private int totalprice;
}
