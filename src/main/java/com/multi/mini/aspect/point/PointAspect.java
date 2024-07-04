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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class PointAspect {
    private final PointService pointService;
    private final MovieService movieService;

    // 영화 예매 시 포인트 적립 추후 수정
    @AfterReturning("execution(* com.multi.mini.payment.service.paymentServiceImpl.markReservationAsPaid(..))")
    public void addPointByTicketing() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

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

        // 업데이트 정보가 담긴 세션을 새로 선언하여 교체
        Authentication updateAuthentication = new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(updateAuthentication);

        System.out.println("영화 예매 포인트 적립 완료");
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
