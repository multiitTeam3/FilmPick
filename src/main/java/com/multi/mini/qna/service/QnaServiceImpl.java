package com.multi.mini.qna.service;

import com.multi.mini.common.model.dto.EmailDTO;
import com.multi.mini.common.service.AuthService;
import com.multi.mini.common.service.EmailService;
import com.multi.mini.qna.model.dto.QnaDTO;
import com.multi.mini.qna.model.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {
    private final QnaMapper qnaMapper;
    private final AuthService authService;

    private final EmailService emailService;


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

    @Override
    public ArrayList<QnaDTO> getQuestionsListAll() throws Exception {
        ArrayList<QnaDTO> qnas = qnaMapper.findQnaListAll();
        if (qnas == null) throw new RuntimeException("문의글 리스트 조회에 실패했습니다.");

        return qnas;
    }

    @Override
    public void sendAnswerEmail(EmailDTO emailDTO, int questionNo) throws Exception{
        emailService.sendEmail(emailDTO);
        int result = qnaMapper.updateQnaStatus(questionNo, true);
        if(result == 0 ) throw new RuntimeException("답변 여부 업데이트에 실패했습니다.");
    }

//    @Override
//    public void sendQuestion(EmailDTO emailDTO) throws Exception {
//        // 이메일 존재 검증
//        MemberDTO userData = authService.isMemberByEmail(emailDTO.getReceiveAddress());
//
//        emailDTO.setMailTitle("FilmPick " + );
//        emailDTO.setMailContent("임시 비밀번호 : " + );
//
//        emailService.sendEmail(emailDTO);
//
//        tempPasswordDTO.setMemberNo(userData.getMemberNo());
//        tempPasswordDTO.setTempPw(tempPassword);
//        tempPasswordDTO.setExpDate(LocalDateTime.now().plusHours(3));
//
//        tempPasswordMapper.insertTempPassword(tempPasswordDTO);
//    }


}
