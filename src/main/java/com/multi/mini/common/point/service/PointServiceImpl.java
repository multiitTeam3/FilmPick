package com.multi.mini.common.point.service;

import com.multi.mini.common.point.model.dto.PointDTO;
import com.multi.mini.common.point.model.mapper.PointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService{
    private final PointMapper pointMapper;

    @Override
    public void addPoints(PointDTO pointDTO) throws Exception{
        int result = pointMapper.insertPoint(pointDTO);
        if(result == 0) throw new RuntimeException("Point DB Insert Error");
    }
}
