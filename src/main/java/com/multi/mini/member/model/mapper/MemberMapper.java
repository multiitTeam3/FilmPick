package com.multi.mini.member.model.mapper;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberDTO> selectAll(PageDTO page);

    @Select("SELECT * FROM MEMBER WHERE MEMBER_NO = #{no}")
    MemberDTO selectMember(int no);

    @Delete("DELETE FROM MEMBER WHERE MEMBER_NO = #{no}")
    int deleteMember(int no);

    @Update("UPDATE MEMBER SET ROLE = #{role} WHERE MEMBER_NO = #{memberNo}")
    int updateMemberRole(MemberDTO userData);
}
