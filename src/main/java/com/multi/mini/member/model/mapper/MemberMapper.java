package com.multi.mini.member.model.mapper;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberDTO> findMemberAll(PageDTO page);

    MemberDTO findMemberByNo(int no);

    @Delete("DELETE FROM MEMBER WHERE MEMBER_NO = #{no}")
    int deleteMember(int no);

    int updateMember(MemberDTO userData);
}
