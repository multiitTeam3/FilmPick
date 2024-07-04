package com.multi.mini.payment.model.dto;

import lombok.Data;

@Data
public class PayProductDTO {
    private int productPaymentNo;
    private int productNo;
    private int paymentsNo;
    private int productQuantity;
    private String posterPath;
    private String productName;
    private int memberNo;
    private String username;
    private int totalPrice;
    private String payMethod;

}

