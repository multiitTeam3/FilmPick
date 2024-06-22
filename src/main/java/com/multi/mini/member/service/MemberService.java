package com.multi.mini.member.service;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.member.model.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    List<MemberDTO> findMemberAll(PageDTO page) throws Exception;

    MemberDTO findMemberByNo(int no) throws Exception;

    void deleteMember(int no);

    void updateMemberRole(MemberDTO userData);
}
