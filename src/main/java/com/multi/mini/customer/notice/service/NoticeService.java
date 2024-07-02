package com.multi.mini.customer.notice.service;

import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.customer.notice.model.dto.NoticeCategoryDTO;
import com.multi.mini.customer.notice.model.dto.NoticeDTO;

import java.util.ArrayList;

public interface NoticeService {

    NoticeDTO getNotice(int noticeNo);

    ArrayList<NoticeDTO> getNoticeList(String type, String keyword, PageDTO pageDTO) throws Exception;

    ArrayList<NoticeCategoryDTO> getCategoryList() throws Exception;
}
