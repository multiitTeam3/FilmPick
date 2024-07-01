package com.multi.mini.admin.notice.service;

import com.multi.mini.customer.notice.model.dto.NoticeDTO;

import java.util.ArrayList;

public interface AdminNoticeService {
    ArrayList<NoticeDTO> getNoticeList() throws Exception;

    void createNotice(NoticeDTO noticeDTO);

    NoticeDTO getNoticeByNoticeNo(int noticeNo);

    void deleteNotice(int noticeNo);

    void updateNotice(NoticeDTO noticeDTO);
}
