package com.multi.mini.community.model.mapper;

import com.multi.mini.community.model.dto.BoardDTO;
import com.multi.mini.common.model.dto.PageDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDTO> selectAll(PageDTO page) throws Exception;
    int insertBoard(BoardDTO boardData) throws Exception;

    BoardDTO selectBoard(int no);

    @Delete("DELETE FROM comm_board WHERE board_no = #{no}")
    int deleteBoard(int no) throws Exception;

    @Update("UPDATE comm_board SET title = #{title}, content = #{content} WHERE board_no = #{boardNo}")
    int updateBoard(BoardDTO boardData) throws Exception;

}
