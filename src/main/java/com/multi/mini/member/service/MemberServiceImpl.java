package com.multi.mini.member.service;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.member.model.dto.MemberDTO;
import com.multi.mini.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;

    @Override
    public List<MemberDTO> findMemberAll(PageDTO page) throws Exception{
        List<MemberDTO> list = memberMapper.findMemberAll(page);
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
    public void deleteMember(int no) {
        int result = memberMapper.deleteMember(no);
        if (result > 0) new Exception("회원 삭제에 실패했습니다.");
    }

    @Override
    public void updateMemberRole(MemberDTO userData) {
        int result = memberMapper.updateMember(userData);
        if (result > 0) new Exception(("회원 정보 수정에 실패했습니다."));
    }
}
