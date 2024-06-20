package com.multi.mini.common.service;

import com.multi.mini.common.model.mapper.AuthMapper;
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
        if (authMapper.selectMemberById(dto.getId()) !=null) throw new Exception("중복된 아이디 입니다.");

        // 비밀번호 암호화
        String bPw = bCryptPasswordEncoder.encode(dto.getPw());
        dto.setPw(bPw);

        // 회원 정보 데이터베이스에 저장
        int result = authMapper.insertMember(dto);
        if (result <= 0) {
            throw new Exception("회원가입에 실패했습니다.");
        }
    }
}
