package com.multi.mini.member.service;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.member.model.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    List<MemberDTO> findMemberAll(String type, String keyword, PageDTO page) throws Exception;

    MemberDTO findMemberByNo(int no) throws Exception;

    void deleteMember(int no) throws Exception;

    void updateMember(MemberDTO userData, String[] roles) throws Exception;

    boolean isMemberByEmail(String email);

    boolean isMemberByName(String name);

    boolean isPassword(String password, CustomUserDetails userDetails);
}
