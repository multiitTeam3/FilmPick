package com.multi.mini.payment.model.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class KakaoReadyDTO {

    private int memberNo;
    private String posterPath;
    private String partnerOrderId;
    private String username;
    private ArrayList<Integer> rsvNoList;
    private VwGetResDataDTO rsv;
    private String movieTitle;
    private String seat;
    private String payMethod;
    private int totalPrice;
    private int adultCount;
    private int teenCount;

}
