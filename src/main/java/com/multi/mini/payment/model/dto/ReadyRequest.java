package com.multi.mini.payment.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReadyRequest {
    private String cid; //가맹점 코드
    private String partnerOrderId; //주문번호
    private String partnerUserId; //회원id
    private String itemName; //상품명
    private Integer quantity;  //상품수량
    private Integer totalAmount; //상품총액
    private Integer taxFreeAmount; //비과세
    private Integer vatAmount; //부가세
    private String approvalUrl; //결제 성공 url
    private String cancelUrl; //결제 취소 url
    private String failUrl; //결제 실패 url
}
