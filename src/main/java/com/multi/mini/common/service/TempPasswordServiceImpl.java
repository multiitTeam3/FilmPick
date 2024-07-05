package com.multi.mini.common.service;

import com.multi.mini.common.model.dto.EmailDTO;
import com.multi.mini.common.model.dto.TempPasswordDTO;
import com.multi.mini.common.model.mapper.TempPasswordMapper;
import com.multi.mini.member.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TempPasswordServiceImpl implements TempPasswordService {
    private final TempPasswordMapper tempPasswordMapper;
    private final AuthService authService;
    private final EmailService emailService;



    @Override
    public void sendTempPassword(EmailDTO emailDTO) throws Exception {
        TempPasswordDTO tempPasswordDTO = new TempPasswordDTO();

        // 이메일 존재 검증
        MemberDTO userData = authService.isMemberByEmail(emailDTO.getReceiveAddress());

        // 임시 비밀번호 생성
        String tempPassword = createTempPassword();

        emailDTO.setMailTitle("FilmPick 임시 비밀번호");
        emailDTO.setMailContent("임시 비밀번호 : " + tempPassword);

        emailService.sendEmail(emailDTO);

        tempPasswordDTO.setMemberNo(userData.getMemberNo());
        tempPasswordDTO.setTempPw(tempPassword);
        tempPasswordDTO.setExpDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusHours(3));

        tempPasswordMapper.insertTempPassword(tempPasswordDTO);
    }

    @Override
    public String createTempPassword() { // 임시 비밀번호 생성
        Random random = new Random();
        int PasswordSize = 8;
        char[] passwordCodeChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuilder tempPassword = new StringBuilder();

        for (int i = 0; i < PasswordSize; i++) {
            int randomNum = random.nextInt(passwordCodeChars.length);
            tempPassword.append(passwordCodeChars[randomNum]);
        }

        return tempPassword.toString();
    }
}
