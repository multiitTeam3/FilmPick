package com.multi.mini.community.service;

import com.multi.mini.community.model.dto.BoardDTO;
import com.multi.mini.community.model.mapper.BoardMapper;
import com.multi.mini.common.model.dto.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> selectBoardAll(PageDTO page) throws Exception {
        List<BoardDTO> listBoard = boardMapper.selectAll(page);
        if (listBoard == null) new Exception("게시글 리스트 조회 실패");

        return listBoard;
    }

    @Override
    public void insertBoard(BoardDTO boardData) throws Exception {
        int result = boardMapper.insertBoard(boardData);
        if (result == 0) new Exception("게시글 리스트 조회 실패");
    }

    @Override
    public BoardDTO findBoardByNo(int no) {
        BoardDTO boardData = boardMapper.selectBoard(no);
        if (boardData != null) new Exception("게시글 상세 조회에 실패했습니다.");
        return boardData;
    }

    @Override
    public void deleteBoard(int no) throws Exception{
        int result = boardMapper.deleteBoard(no);
        if (result > 0) new Exception("게시글 삭제에 실패했습니다.");
    }

    @Override
    public void updateBoard(BoardDTO boardData) throws Exception{
        int result = boardMapper.updateBoard(boardData);
        if (result > 0) new Exception(("게시글 수정에 실패했습니다."));
    }
}
