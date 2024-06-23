package com.multi.mini.member.model.mapper;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberDTO> findMemberAll(PageDTO page);

    MemberDTO findMemberByNo(int no);

    @Delete("DELETE FROM mem_member WHERE member_no = #{no}")
    int deleteMember(int no);

    @Insert("INSERT INTO mem_member_and_role (member_no, role_no) VALUES (#{memberNo}, #{roleNo})")
    int insertMemberRole(@Param("memberNo") int memberNo, @Param("roleNo") int roleNo);

    @Delete("DELETE FROM mem_member_and_role WHERE member_no = #{no}")
    int deleteMemberRole(int no);

    int updateMember(MemberDTO userData);
}
