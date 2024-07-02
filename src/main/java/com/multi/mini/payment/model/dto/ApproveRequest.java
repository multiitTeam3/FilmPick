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
//카카오페이 결제를 시작하기 위해 결제정보를 카카오페이 서버에 전달하고
// 결제 고유번호(TID)와 URL을 응답받는 단계입니다.
public class ApproveRequest {
    private String cid; //가맹점 코드
    private String tid; //결제 고유 코드
    private String partnerOrderId; //가맹점 주문번호
    private String partnerUserId; //가맹점 회원 id
    private String pgToken;  // 결제 승인 요청 시 발급되는 토큰 (사용자 인증 완료 후 결제승인 요청에 필요한 토큰)

}
