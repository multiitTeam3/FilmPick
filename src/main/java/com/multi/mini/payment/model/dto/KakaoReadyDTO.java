package com.multi.mini.payment.model.dto;

import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.movie.model.dto.ReservationDTO;
import lombok.Data;

import java.sql.Timestamp;
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
    private String productName;
    private String seat;
    private String payMethod;
    private int totalPrice;
    private int adultCount;
    private int teenCount;

}
