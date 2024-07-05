package com.multi.mini.member.service;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<MemberDTO> findMemberAll(String type, String keyword, PageDTO page) throws Exception{
        List<MemberDTO> list = memberMapper.findMemberAll(type, keyword, page.getStart(), page.getEnd());
        if (list != null) new Exception("회원 리스트 조회에 실패했습니다.");
        return list;
    }

    @Override
    public MemberDTO findMemberByNo(int no) throws Exception{
        MemberDTO userData = memberMapper.findMemberByNo(no);
        if (userData != null) new Exception("회원 조회에 실패했습니다.");
        return userData;
    }

    @Override
    public void deleteMember(int no) throws Exception{
        int resultRole = memberMapper.deleteMemberRole(no);
        int resultMember = memberMapper.deleteMember(no);
        if (resultRole == resultMember) new Exception("회원 삭제에 실패했습니다.");
    }

    @Override
    public void updateMember(MemberDTO userData, String[] roles) throws Exception{
        int deleteRoleResult = 0;
        int insertRoleResult = 0;
        int updateMemberResult = 0;

        if(roles != null) { // 관리자 회원 정보 수정
            deleteRoleResult = memberMapper.deleteMemberRole(userData.getMemberNo());

            for( String roleName : roles) {
                switch (roleName) {
                    case "ROLE_STAFF":
                        insertRoleResult = memberMapper.insertMemberRole(userData.getMemberNo(), 2);
                        break;
                    case "ROLE_USER":
                        insertRoleResult = memberMapper.insertMemberRole(userData.getMemberNo(), 3);
                        break;
                }
            }
            updateMemberResult = memberMapper.updateMember(userData);
            if(deleteRoleResult + insertRoleResult + updateMemberResult != 3) new Exception("회원 수정에 실패했습니다.");
        } else { // 사용자 회원 정보 수정
//            if(!loginUserEmail.equals(updateUserEmail)) throw new Exception("사용자 정보가 다릅니다."); // 로그인한 사용자와 변경할 유저 Email이 동일한지 체크, 세션에서 가져오므로 불필요

            if(userData.getPassword() != null) { // 사용자 패스워드 변경 시
                String bPw = bCryptPasswordEncoder.encode(userData.getPassword());
                userData.setPassword(bPw);
            }
            updateMemberResult = memberMapper.updateMember(userData);
            if(updateMemberResult != 3) new Exception("회원 수정에 실패했습니다.");
        }
    }

    @Override
    public boolean isMemberByEmail(String email) {
        return memberMapper.findMemberByEmail(email) != null;
    }

    @Override
    public boolean isMemberByName(String name) {
        return memberMapper.findMemberByName(name) != null;
    }

    @Override
    public boolean isPassword(String password, CustomUserDetails userDetails) {
        boolean passwordMatches = passwordEncoder.matches(password, userDetails.getPassword()) ||
                (password.equals(userDetails.getTempPassword()) && // 임시 비밀번호 일치
                        userDetails.getTempExpDate().isAfter(LocalDateTime.now())); // 만료시간이 현재시간 이전인지 확인
        return  passwordMatches;
    }
}
