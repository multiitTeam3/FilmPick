package com.multi.mini.common.service;

import com.multi.mini.member.model.dto.MemberDTO;

public interface AuthService {
    void createMember(MemberDTO dto) throws Exception;
}
