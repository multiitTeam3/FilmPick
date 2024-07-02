package com.multi.mini.payment.service;


import com.multi.mini.payment.model.dto.ApproveRequest;
import com.multi.mini.payment.model.dto.ReadyRequest;
import com.multi.mini.payment.model.dto.ReadyResponse;
import com.multi.mini.payment.model.dto.KakaoReadyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static java.rmi.server.LogStream.log;


@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

    private static final String Host = "https://open-api.kakaopay.com/online/v1/payment/ready";

    private static final String Hostapprove = "https://open-api.kakaopay.com/online/v1/payment/approve";


    @Value("${kakaopay.api.secret.key}")
    private String kakaopaySecretKey;

    @Value("${cid}")
    private String cid;

    @Value("${sample.host}")
    private String sampleHost;

    private ReadyResponse readyResponse;

    private String tid; // 결제 고유 번호(TID)를 저장하는 변수


    public String kakaoPayReady(KakaoReadyDTO kakaoReadyDTO) {
        RestTemplate restTemplate = new RestTemplate();

        // Server Request Header : 서버 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "DEV_SECRET_KEY " + kakaopaySecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String itemName  = kakaoReadyDTO.getMovieTitle();
        int quantity = kakaoReadyDTO.getAdultCount() + kakaoReadyDTO.getTeenCount();
        int totalAmount = kakaoReadyDTO.getTotalPrice();
        String partnerOrderId = kakaoReadyDTO.getPartnerOrderId();
        String partnerUserId = kakaoReadyDTO.getUsername();



        System.out.println("itemName "+itemName);
        System.out.println("quantity "+quantity);
        System.out.println("totalAmount "+totalAmount);
        System.out.println("partnerOrderId "+partnerOrderId);
        System.out.println("partnerUserId "+partnerUserId);

        System.out.println("결제준비 데이터확인2 :"+kakaoReadyDTO.toString());


        ReadyRequest readyRequest = ReadyRequest.builder()
                .cid("TC0ONETIME")
                .partnerOrderId(partnerOrderId)
                .partnerUserId(partnerUserId)
                .itemName(itemName)
                .quantity(quantity)
                .totalAmount(totalAmount)
                .taxFreeAmount(0)
                .approvalUrl("http://localhost:8099/payment/kakaoPaySuccess?partnerOrderId=" + partnerOrderId)
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

    public String approve(String pgToken, KakaoReadyDTO kakaoReadyDTO) {
        // ready할 때 저장해놓은 TID로 승인 요청
        // Call “Execute approved payment” API by pg_token, TID mapping to the current payment transaction and other parameters.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY " + kakaopaySecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println("approve: "+ kakaoReadyDTO.getPartnerOrderId());
        System.out.println("approve: "+ kakaoReadyDTO.getUsername());
        System.out.println("approve: "+ tid);

        // Request param
        ApproveRequest approveRequest = ApproveRequest.builder()
                .cid(cid)
                .tid(tid)
                .partnerOrderId(kakaoReadyDTO.getPartnerOrderId())
                .partnerUserId(kakaoReadyDTO.getUsername())
                .pgToken(pgToken)
                .build();

        // Send Request
        HttpEntity<ApproveRequest> entityMap = new HttpEntity<>(approveRequest, headers);
        try {
            ResponseEntity<String> response = new RestTemplate().postForEntity(
                    Hostapprove,
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