package com.multi.mini.qna.model.mapper;

import com.multi.mini.qna.model.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface QnaMapper {

    int insertQna(QnaDTO qnaDTO) throws Exception;

    List<QnaDTO> selectAll(int member_no) throws Exception;


    QnaDTO selectQna(int no);

    ArrayList<QnaDTO> findQnaListAll();

    @Update("UPDATE admin_question SET is_answer = #{ isAnswer } WHERE qna_no = #{ questionNo }")
    int updateQnaStatus(@Param("questionNo") int questionNo, @Param("isAnswer") boolean isAnswer);
}
