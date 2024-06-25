package com.multi.mini.payment.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentsDTO {

    private Integer payments_no;
    private Integer member_no;
    private String pay_method;
    private Long ord_code;
    private Timestamp day_date;


}
