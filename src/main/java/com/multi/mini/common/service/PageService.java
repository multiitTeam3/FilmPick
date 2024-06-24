package com.multi.mini.common.service;

import com.multi.mini.common.model.dto.PageDTO;

public interface PageService {
    int selectMemberCount() throws Exception;

    int selectBoardCount(PageDTO page) throws Exception;

    int selectQnaCount(PageDTO page) throws Exception;
}
