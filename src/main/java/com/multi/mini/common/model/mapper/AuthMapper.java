package com.multi.mini.common.model.mapper;

import com.multi.mini.member.model.dto.MemberAndRoleDTO;
import com.multi.mini.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    int insertMember(MemberDTO dto);
    MemberDTO selectMemberByEmail(String email);

    int insertMemberAndRole(MemberAndRoleDTO memberAndRoleData);
}
