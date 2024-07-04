package com.multi.mini.common.service;

import com.multi.mini.common.model.mapper.AuthMapper;
import com.multi.mini.member.model.dto.CustomUserDetails;
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

        // 이메일 중복 검증
        checkMemberByEmail(email);

        // 비밀번호 암호화
        String bPw = bCryptPasswordEncoder.encode(dto.getPassword());
        dto.setPassword(bPw);

        // 회원 정보 데이터베이스에 저장
        int result = authMapper.insertMember(dto);

        if (result > 0) {
            // 회원 정보 불러오기
            MemberDTO userData = authMapper.selectMemberByEmail(email);

            // 권한 관련 DTO
            MemberAndRoleDTO memberAndRoleData = new MemberAndRoleDTO();
            memberAndRoleData.setMemberNo(userData.getMemberNo());
            memberAndRoleData.setRoleNo(3);

            int roleResult = authMapper.insertMemberAndRole(memberAndRoleData);
            if (roleResult == 0) throw new Exception("권한 추가에 실패했습니다.");
        } else {
            throw new Exception("회원가입에 실패했습니다.");
        }
    }

    // 이메일 존재 검증
    public void checkMemberByEmail(String memberEmail) throws Exception {
        MemberDTO userData = authMapper.selectMemberByEmail(memberEmail);
        if (userData != null) {
            throw new RuntimeException("이미 존재하는 이메일 입니다.");
        }
    }
    
    // 이메일 존재 확인
    public MemberDTO isMemberByEmail(String memberEmail) throws Exception {
        MemberDTO userData = authMapper.selectMemberByEmail(memberEmail);
        if (userData == null) {
            throw new RuntimeException("존재하지 않는 이메일입니다.");
        }
        return userData;
    }

    // 패스워드 변경
    @Override
    public boolean changePassword(String password, CustomUserDetails userDetails) {
        MemberDTO memberDTO = new MemberDTO();

        // 패스워드 암호화
        String bPw = bCryptPasswordEncoder.encode(password);

        System.out.println("비밀번호 " + bPw);
        memberDTO.setMemberNo(userDetails.getMemberNo());
        memberDTO.setPassword(bPw);

        return authMapper.updatePassword(memberDTO);
    }
}
