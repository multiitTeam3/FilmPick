package com.multi.mini.payment.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentsDTO {

    private int paymentsNo;
    private int memberNo;
    private String payMethod;
    private String ordCode;
    private int amount;
    private int discount;
    private int totalPrice;
    private Timestamp dayDate;


}
