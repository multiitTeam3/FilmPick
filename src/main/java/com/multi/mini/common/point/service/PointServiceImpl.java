package com.multi.mini.common.point.service;

import com.multi.mini.common.point.model.dto.PointDTO;
import com.multi.mini.common.point.model.mapper.PointMapper;
import com.multi.mini.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService{
    private final PointMapper pointMapper;
    private final MemberMapper memberMapper;

    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public void addPoints(PointDTO pointDTO) throws Exception{
        // 포인트 변동 DB에 적용
        int PointUpdateResult = memberMapper.updatePoint(pointDTO);
        int pointResult = pointMapper.insertPoint(pointDTO);
        
        if(pointResult != PointUpdateResult) throw new RuntimeException("Point DB Insert Error");
    }
}
