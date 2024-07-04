package com.multi.mini.payment.model.dto;


import lombok.Data;

@Data
public class PayProductAndPaymentDTO {

    private int productPaymentNo;
    private int papProductNo;
    private int papPayNo;
    private int productQuantity;
    private String productName;
    
}
