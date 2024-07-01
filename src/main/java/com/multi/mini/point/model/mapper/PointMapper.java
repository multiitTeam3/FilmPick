package com.multi.mini.point.model.mapper;

import com.multi.mini.point.model.dto.Point;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointMapper {
    int insertPoint(Point point);
}
