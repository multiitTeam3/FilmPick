package com.multi.mini.admin.coupon.service;

import com.multi.mini.admin.coupon.model.dto.CouponDTO;
import com.multi.mini.admin.coupon.model.mapper.CouponMapper;
import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CouponServiceImpl implements CouponService{
    private final CouponMapper couponMapper;
    private final MemberMapper memberMapper;

    @Override
    public void insertCoupon(CouponDTO couponDTO, String email, int discount, String desc, int expDate) throws Exception {
        // 날짜 만료 날짜 생성
        LocalDate nowDate = LocalDate.now().plusDays(expDate);

        MemberDTO memberDTO = memberMapper.findMemberByEmail(email);

        // 존재하는 회원인지 검증
        if (memberDTO == null) throw new Exception("존재하지 않는 사용자 입니다.");

        // 쿠폰 DTO 정보 세팅
        couponDTO.setDiscount(discount);
        couponDTO.setExpDate(Date.valueOf(nowDate));
        couponDTO.setDescription(desc);
        couponDTO.setMember(memberDTO);

        Random random = new Random();
        int couponSize = 16;
        char[] couponCodeChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuilder couponCode = new StringBuilder();

        while (true) { // 쿠폰 생성
            couponCode.setLength(0);

            for (int i = 0; i < couponSize; i++) {
                int randomNum = random.nextInt(couponCodeChars.length);
                couponCode.append(couponCodeChars[randomNum]);
            }

            // 쿠폰 코드 중복 확인
            if (couponMapper.findCouponByCode(couponCode.toString()) == null) break;
        }
        couponDTO.setCouponCode(couponCode.toString());

        if (couponMapper.insetCoupon(couponDTO) == 0) throw  new RuntimeException("쿠폰 생성 실패");
    }

    @Override
    public List<CouponDTO> findCouponAll(String type, String keyword, PageDTO pageDTO) throws Exception{
        List<CouponDTO> list = couponMapper.findCouponAll(type, keyword, pageDTO.getStart(), pageDTO.getEnd());
        return list;
    }

    @Override
    public void deleteCoupon(String couponCode) throws Exception{
        if(couponMapper.deleteCoupon(couponCode) == 0) throw new RuntimeException("쿠폰 삭제 실패");
    }

    @Override
    public ArrayList<CouponDTO> findCouponListByMemberNo(int memberNo) throws Exception{
        ArrayList<CouponDTO> list = (ArrayList<CouponDTO>) couponMapper.findCouponListByMemberNo(memberNo);
        return list;
    }
}
