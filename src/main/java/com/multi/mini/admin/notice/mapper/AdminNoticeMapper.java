package com.multi.mini.admin.notice.mapper;

import com.multi.mini.customer.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface AdminNoticeMapper {
    ArrayList<NoticeDTO> findNoticeAll();

    int insertNotice(NoticeDTO noticeDTO);

    NoticeDTO findNoticeByNoticeNo(int noticeNo);

    int updateNotice(NoticeDTO noticeDTO);

    int deleteNotice(int noticeNo);
}
