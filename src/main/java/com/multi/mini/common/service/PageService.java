package com.multi.mini.common.service;

import com.multi.mini.common.model.dto.PageDTO;

public interface PageService {
    int selectMemberCount(String type, String keyword) throws Exception;

    int selectBoardCount(PageDTO page) throws Exception;

    int selectCinemaCount(String type, String keyword);

    int selectQuestionCount(String type, String keyword);

    int selectCouponCount(String type, String keyword);
}
