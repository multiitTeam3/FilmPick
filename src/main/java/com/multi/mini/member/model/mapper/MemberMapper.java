package com.multi.mini.member.model.mapper;

import com.multi.mini.common.point.model.dto.PointDTO;
import com.multi.mini.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberDTO> findMemberAll(@Param("type") String type, @Param("keyword") String keyword, @Param("start") int start, @Param("end") int end);

    MemberDTO findMemberByNo(int no);

    @Delete("DELETE FROM mem_member WHERE member_no = #{no}")
    int deleteMember(int no);

    @Insert("INSERT INTO mem_member_and_role (member_no, role_no) VALUES (#{memberNo}, #{roleNo})")
    int insertMemberRole(@Param("memberNo") int memberNo, @Param("roleNo") int roleNo);

    @Delete("DELETE FROM mem_member_and_role WHERE member_no = #{no}")
    int deleteMemberRole(int no);

    int updateMember(MemberDTO userData);

    @Select("SELECT member_no FROM mem_member WHERE email = #{ userEmail }")
    MemberDTO findMemberByEmail(String userEmail);

    @Update("UPDATE mem_member " +
            "SET point = point + #{ pointChange } " +
            "WHERE member_no = #{ memberNo }")
    int updatePoint(PointDTO memberNo);

    @Select("SELECT member_no FROM mem_member WHERE user_name = #{ name }")
    MemberDTO findMemberByName(String name);
}
