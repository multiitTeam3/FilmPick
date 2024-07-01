package com.multi.mini.qna.service;

import com.multi.mini.qna.model.dto.QnaDTO;
import com.multi.mini.qna.model.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {
    private final QnaMapper qnaMapper;


    @Override
    public void insertQna(QnaDTO qnaDTO) throws Exception {
        int result = qnaMapper.insertQna(qnaDTO);
        if (result == 0) new Exception("문의 등록 실패");
    }

    @Override
    public List<QnaDTO> selectQnaAll(int member_no) throws Exception {
        List<QnaDTO> listQna= qnaMapper.selectAll(member_no);
        if (listQna == null) new Exception("문의글 리스트 조회 실패");

        return listQna;
    }

    @Override
    public QnaDTO findQnaByNo(int no) throws Exception {
        QnaDTO qnaDTO = qnaMapper.selectQna(no);
        if (qnaDTO != null) new Exception("문의글 상세 조회에 실패했습니다.");
        return qnaDTO;
    }


}
