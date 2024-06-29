package com.multi.mini.qna.service;

import com.multi.mini.common.model.dto.EmailDTO;
import com.multi.mini.qna.model.dto.QnaDTO;

import java.util.ArrayList;
import java.util.List;

public interface QnaService {

    void insertQna(QnaDTO qnaDTO) throws Exception;

    List<QnaDTO> selectQnaAll(int member_no) throws Exception;


    QnaDTO findQnaByNo(int no) throws Exception;

    ArrayList<QnaDTO> getQuestionsListAll() throws Exception;

//    void sendQuestion(EmailDTO emailDTO) throws Exception;

    void sendAnswerEmail(EmailDTO emailDTO, int questionNo) throws Exception;
}
