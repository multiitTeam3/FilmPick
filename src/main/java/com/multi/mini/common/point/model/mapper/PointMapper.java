package com.multi.mini.common.point.model.mapper;

import com.multi.mini.common.point.model.dto.PointDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointMapper {

    @Insert("INSERT INTO mem_point_transaction (member_no, point_change, point_description) " +
            "VALUES (#{memberNo}, #{pointChange}, #{pointDesc})")
    int insertPoint(PointDTO pointDTO) throws Exception;
}