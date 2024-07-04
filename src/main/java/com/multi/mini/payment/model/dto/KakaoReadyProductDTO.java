package com.multi.mini.payment.model.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class KakaoReadyProductDTO {

    private int memberNo;
    private String partnerOrderId;
    private String username;
    private String payMethod;

    private PayProductDTO payProductDTO;
}
