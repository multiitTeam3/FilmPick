package com.multi.mini.common.point.model.mapper;

import com.multi.mini.common.point.model.dto.PointDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointMapper {
    int insertPoint(PointDTO pointDTO) throws Exception;
}
