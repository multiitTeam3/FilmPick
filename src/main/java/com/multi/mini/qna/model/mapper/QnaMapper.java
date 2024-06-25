package com.multi.mini.qna.model.mapper;

import com.multi.mini.qna.model.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {

    int insertQna(QnaDTO qnaDTO) throws Exception;

    List<QnaDTO> selectAll(int member_no) throws Exception;


    QnaDTO selectQna(int no);
}
