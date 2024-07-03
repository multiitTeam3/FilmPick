package com.multi.mini.point;


import com.multi.mini.common.point.model.dto.PointDTO;
import com.multi.mini.common.point.service.PointService;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class PointAspect {
    private final PointService pointService;

    // 영화 예매 시 포인트 적립
    @AfterReturning("execution(* com.multi.mini.payment.service.paymentService.markReservationAsPaid())")
    public void addPointByTicketing() {
        CustomUserDetails userDetails = SecurityUtil.getUserDetails();
        int userNo = userDetails.getMemberNo();
        int point = ticketingPoint();
        String description = "영화 예매";

        PointDTO pointDTO = new PointDTO();
        pointDTO.setMemberNo(userNo);
        pointDTO.setPointChange(point);
        pointDTO.setPointDesc(description);

        log.info("log info point insert start = {}", pointDTO);
        try {
            log.debug("log debug point controller = {}", pointDTO);
            pointService.addPoints(pointDTO);
        } catch (Exception e) {
            log.error("log error point insert = ", e);
        }
        System.out.println("영화 예매 포인트 적립 완료");
    }

    // 영화 리뷰 시 포인트 적립
    @AfterReturning("execution(* com.multi.mini.movie.service.MovieService.insertReview())")
    public void addPointByReview() {
        CustomUserDetails userDetails = SecurityUtil.getUserDetails();
        int userNo = userDetails.getMemberNo();
        int point = reviewPoint();
        String description = "영화 리뷰";

        PointDTO pointDTO = new PointDTO();
        pointDTO.setMemberNo(userNo);
        pointDTO.setPointChange(point);
        pointDTO.setPointDesc(description);

        log.info("log info point insert start = {}", pointDTO);
        try {
            log.debug("log debug point controller = {}", pointDTO);
            pointService.addPoints(pointDTO);
        } catch (Exception e) {
            log.error("log error point insert = ", e);
        }
        System.out.println("영화 리뷰 포인트 적립 완료");
    }

    private int ticketingPoint() {
        return 50;
    }

    // 리뷰 시 포인트 계산
    private int reviewPoint() {
        return 20;
    }



}
