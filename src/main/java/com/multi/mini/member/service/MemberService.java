package com.multi.mini.member.service;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.member.model.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    List<MemberDTO> selectAll(PageDTO page) throws Exception;

    MemberDTO findUserByNo(int no) throws Exception;

    void deleteMember(int no);

    void updateMemberRole(MemberDTO userData);
}
