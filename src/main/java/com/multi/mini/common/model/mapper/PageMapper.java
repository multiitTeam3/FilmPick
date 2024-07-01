package com.multi.mini.common.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PageMapper {
    @Select("SELECT COUNT(*) FROM mem_member")
    int selectMemberCount() throws Exception;

    @Select("SELECT COUNT(*) FROM comm_board")
    int selectBoardCount() throws Exception;

    @Select("SELECT COUNT(*) FROM admin_question")
    int selectQnaCount() throws Exception;
}
