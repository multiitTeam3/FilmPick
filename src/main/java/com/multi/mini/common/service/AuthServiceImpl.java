package com.multi.mini.common.service;

import com.multi.mini.common.model.mapper.AuthMapper;
import com.multi.mini.member.model.dto.MemberAndRoleDTO;
import com.multi.mini.member.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService{
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthMapper authMapper;
    @Override
    public void createMember(MemberDTO dto) throws Exception {
        String email = dto.getEmail();
        if (authMapper.selectMemberByEmail(email) !=null) throw new Exception("중복된 이메일 입니다.");

        // 비밀번호 암호화
        String bPw = bCryptPasswordEncoder.encode(dto.getPassword());
        dto.setPassword(bPw);

        // 회원 정보 데이터베이스에 저장
        int result = authMapper.insertMember(dto);

        if (result > 0) {
            // 넣은 회원 정보 불러오기
            MemberDTO userData = authMapper.selectMemberByEmail(email);

            // 권한 관련 DTO
            MemberAndRoleDTO memberAndRoleData = new MemberAndRoleDTO();
            memberAndRoleData.setMemberNo(userData.getMemberNo());
            memberAndRoleData.setRoleNo(2);

            int roleResult = authMapper.insertMemberAndRole(memberAndRoleData);
            if (roleResult == 0) throw new Exception("권한 추가에 실패했습니다.");
        } else {
            throw new Exception("회원가입에 실패했습니다.");
        }


    }
}
