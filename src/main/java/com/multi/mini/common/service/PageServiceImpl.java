package com.multi.mini.common.service;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.common.model.mapper.PageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService{
    private final PageMapper pageMapper;

    @Override
    public int selectMemberCount(String type, String keyword) throws Exception {
        int count = pageMapper.selectMemberCount(type, keyword);
        return count;
    }

    @Override
    public int selectBoardCount(PageDTO page) throws Exception {
        int count = pageMapper.selectBoardCount();
        return count;
    }

    @Override // 영화관 카운트
    public int selectCinemaCount(String type, String keyword) {
        int count = pageMapper.selectCinemaCount(type, keyword);
        return count;
    }

    @Override
    public int selectQuestionCount(String type, String keyword) {
        int count = pageMapper.selectQuestionCount(type, keyword);
        return count;
    }

    @Override
    public int selectCouponCount(String type, String keyword) {
        int count = pageMapper.selectCouponCount(type, keyword);
        return count;
    }

    @Override
    public int selectNoticeCount(String type, String keyword) {
        int count = pageMapper.selectNoticeCount(type, keyword);
        return count;
    }

}
