package com.multi.mini.customer.notice.service;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.customer.notice.model.dto.NoticeCategoryDTO;
import com.multi.mini.customer.notice.model.dto.NoticeDTO;
import com.multi.mini.customer.notice.model.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;

    @Override
    public ArrayList<NoticeDTO> getNoticeList(String type, String keyword, PageDTO pageDTO) throws Exception{
        ArrayList<NoticeDTO> notices = noticeMapper.findNoticeAll(type);
        if(notices == null) throw new RuntimeException("공지 목록 조회 실패");
        return notices;
    }

    @Override
    public ArrayList<NoticeCategoryDTO> getCategoryList() throws Exception {
        ArrayList<NoticeCategoryDTO> categorys = noticeMapper.findNoticeCategoryAll();
        if(categorys == null) throw new RuntimeException("카테고리 목록 조회 실패");
        return categorys;
    }

    @Override
    public NoticeDTO getNotice(int noticeNo) {
        NoticeDTO notice = noticeMapper.findNoticeByNo(noticeNo);
        if(notice == null) throw new RuntimeException("공지 조회 실패");
        return notice;
    }
}
