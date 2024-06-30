package com.multi.mini.payment.model.dto;

import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.movie.model.dto.ReservationDTO;
import lombok.Data;

import java.util.ArrayList;

@Data
public class KakaoReadyDTO {

    private int memberNo;
    private String partnerOrderId;
    private String username;
    private ArrayList<Integer> rsvNoList;
    private VwGetResDataDTO rsv;
    private String movieTitle;
    private int totalPrice;
    private int adultCount;
    private int teenCount;

}
