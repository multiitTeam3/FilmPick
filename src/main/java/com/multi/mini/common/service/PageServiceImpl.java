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
    public int selectMemberCount() throws Exception {
        int count = pageMapper.selectMemberCount();
        return count;
    }

    @Override
    public int selectBoardCount(PageDTO page) throws Exception {
        int count = pageMapper.selectBoardCount();
        return count;
    }
}
