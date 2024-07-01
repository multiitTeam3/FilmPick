package com.multi.mini.point.service;

import com.multi.mini.point.model.dto.Point;
import com.multi.mini.point.model.mapper.PointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService{
    private final PointMapper pointMapper;

    public void addPoint() {
        Point point = new Point();
        int result = pointMapper.insertPoint(point);

    }
}
