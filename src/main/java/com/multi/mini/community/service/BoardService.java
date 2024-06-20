package com.multi.mini.community.service;

import com.multi.mini.community.model.dto.BoardDTO;
import com.multi.mini.common.model.dto.PageDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> selectBoardAll(PageDTO page) throws Exception;
    void insertBoard(BoardDTO boardData) throws Exception;

    BoardDTO findBoardByNo(int no) throws Exception;

    void deleteBoard(int no) throws Exception;

    void updateBoard(BoardDTO boardData) throws Exception;
}
