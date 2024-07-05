package com.multi.mini.aspect.point;


import com.multi.mini.common.point.model.dto.PointDTO;
import com.multi.mini.common.point.service.PointService;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.movie.model.dto.ReviewDTO;
import com.multi.mini.movie.service.MovieService;
import com.multi.mini.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class PointAspect {
    private final PointService pointService;
    private final MovieService movieService;

    // 영화 예매 시 포인트 적립
    @AfterReturning("execution(* com.multi.mini.payment.service.KakaoPayService.approveProduct(..))")
    public void addPointByTicketing() {
        CustomUserDetails userDetails = SecurityUtil.getUserDetails();

        int userNo = userDetails.getMemberNo();
        int point = ticketingPoint();
        String description = "영화 예매 또는 상품 구매";

        PointDTO pointDTO = new PointDTO();
        pointDTO.setMemberNo(userNo);
        pointDTO.setPointChange(point);
        pointDTO.setPointDesc(description);

        log.info("log info point insert start = {}", pointDTO);
        try {
            log.debug("log debug point controller = {}", pointDTO);
            pointService.addPoints(pointDTO);
            System.out.println("영화 예매 or 상품 구매 포인트 적립 완료");
        } catch (Exception e) {
            log.error("log error point insert = ", e);
        }
    }

    // 영화 리뷰 시 포인트 적립
    @AfterReturning("execution(* com.multi.mini.movie.service.MovieServiceImpl.insertReview(..))")
    public void addPointByReview(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ReviewDTO reviewDTO = (ReviewDTO) args[0];

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
            if(movieService.findReviewByMemberNoAndMovieNo(reviewDTO) == null) pointService.addPoints(pointDTO);
            System.out.println("영화 리뷰 포인트 적립 완료");
        } catch (Exception e) {
            log.error("log error point insert = ", e);
        }
    }

    private int ticketingPoint() {
        return 50;
    }

    // 리뷰 시 포인트 계산
    private int reviewPoint() {
        return 20;
    }
}
