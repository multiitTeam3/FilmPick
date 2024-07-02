package com.multi.mini.customer.notice.model.mapper;

import com.multi.mini.customer.notice.model.dto.NoticeCategoryDTO;
import com.multi.mini.customer.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface NoticeMapper {
    ArrayList<NoticeDTO> findNoticeAll(String type);

    NoticeDTO findNoticeByNo(int noticeNo);

    ArrayList<NoticeCategoryDTO> findNoticeCategoryAll();
}
