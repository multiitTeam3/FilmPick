package com.multi.mini.admin.notice.service;

import com.multi.mini.admin.notice.mapper.AdminNoticeMapper;
import com.multi.mini.customer.notice.model.dto.NoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class AdminNoticeServiceImpl implements AdminNoticeService{
    private final AdminNoticeMapper adminNoticeMapper;

    @Override
    public ArrayList<NoticeDTO> getNoticeList() throws Exception{
        ArrayList<NoticeDTO> notices = adminNoticeMapper.findNoticeAll();
        if(notices == null) throw new RuntimeException("공지사항 DB 조회에 실패했습니다");

        return notices;
    }

    @Override
    public void createNotice(NoticeDTO noticeDTO) {
        int result = adminNoticeMapper.insertNotice(noticeDTO);
        if(result == 0) throw new RuntimeException("공지사항 작성에 실패했습니다.");
    }

    @Override
    public NoticeDTO getNoticeByNoticeNo(int noticeNo) {
        NoticeDTO noticeDTO = adminNoticeMapper.findNoticeByNoticeNo(noticeNo);
        if(noticeDTO == null) throw new RuntimeException("공지사항 조회에 실패했습니다.");
        return noticeDTO;
    }

    @Override
    public void deleteNotice(int noticeNo) {
        int result = adminNoticeMapper.deleteNotice(noticeNo);
        if(result == 0) throw new RuntimeException("공지사항 삭제에 실패했습니다.");
    }

    @Override
    public void updateNotice(NoticeDTO noticeDTO) {
        int result = adminNoticeMapper.updateNotice(noticeDTO);
        if(result == 0) throw new RuntimeException("공지사항 업데이트에 실패했습니다.");
    }
}
