package com.multi.mini.common.service;

import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.member.model.dto.MemberDTO;

public interface AuthService {
    void createMember(MemberDTO dto) throws Exception;

    MemberDTO isMemberByEmail(String memberEmail) throws Exception;

    boolean changePassword(String password, CustomUserDetails userDetails);
}
