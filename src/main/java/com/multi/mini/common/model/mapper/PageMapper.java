package com.multi.mini.common.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PageMapper {
    int selectMemberCount(@Param("type") String type, @Param("keyword") String keyword) throws Exception;

    @Select("SELECT COUNT(*) FROM comm_board")
    int selectBoardCount() throws Exception;

    @Select("SELECT COUNT(*) FROM admin_question")
    int selectQnaCount() throws Exception;

    int selectCinemaCount(@Param("type") String type, @Param("keyword") String keyword);

    int selectQuestionCount(@Param("type") String type, @Param("keyword") String keyword);

    int selectCouponCount(@Param("type") String type, @Param("keyword") String keyword);
}
