package com.multi.mini.kakaopayment.service;


import com.multi.mini.kakaopayment.model.ApproveRequest;
import com.multi.mini.kakaopayment.model.ReadyRequest;
import com.multi.mini.kakaopayment.model.ReadyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

    private static final String Host = "https://open-api.kakaopay.com/online/v1/payment/ready";


    @Value("${kakaopay.api.secret.key}")
    private String kakaopaySecretKey;

    @Value("${cid}")
    private String cid;

    @Value("${sample.host}")
    private String sampleHost;

    private ReadyResponse readyResponse;

    private String tid; // 결제 고유 번호(TID)를 저장하는 변수

    public String kakaoPayReady() {
        RestTemplate restTemplate = new RestTemplate();

        // Server Request Header : 서버 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "DEV_SECRET_KEY " + kakaopaySecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);


        ReadyRequest readyRequest = ReadyRequest.builder()
                .cid("TC0ONETIME")
                .partnerOrderId("1001")
                .partnerUserId("goguma")
                .itemName("비둘기")
                .quantity(1)
                .totalAmount(20000000)
                .taxFreeAmount(100)
                .approvalUrl("http://localhost:8099/payment/kakaoPaySuccess")
                .cancelUrl("http://localhost:8099/payment/kakaoPayCancel")
                .failUrl("http://localhost:8099/payment/kakaoPayFail")
                .build();
        // 헤더와 바디 붙이기
        HttpEntity<ReadyRequest> body = new HttpEntity<>(readyRequest, headers);
        ResponseEntity<ReadyResponse> response = new RestTemplate().postForEntity(
                Host,
                body,
                ReadyResponse.class
        );
        ReadyResponse readyResponse = response.getBody();

        // 주문번호와 TID를 매핑해서 저장해놓는다.
        this.tid = readyResponse.getTid();
        //결제 경로 얼어줌
        return readyResponse.getNext_redirect_pc_url();
    }

    public String approve(String pgToken) {
        // ready할 때 저장해놓은 TID로 승인 요청
        // Call “Execute approved payment” API by pg_token, TID mapping to the current payment transaction and other parameters.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY " + kakaopaySecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Request param
        ApproveRequest approveRequest = ApproveRequest.builder()
                .cid(cid)
                .tid(tid)
                .partnerOrderId("1")
                .partnerUserId("1")
                .pgToken(pgToken)
                .build();

        // Send Request
        HttpEntity<ApproveRequest> entityMap = new HttpEntity<>(approveRequest, headers);
        try {
            ResponseEntity<String> response = new RestTemplate().postForEntity(
                    Host,
                    entityMap,
                    String.class
            );

            // 승인 결과를 저장한다.
            // save the result of approval
            String approveResponse = response.getBody();
            return approveResponse;
        } catch (HttpStatusCodeException ex) {
            return ex.getResponseBodyAsString();
        }
    }


}