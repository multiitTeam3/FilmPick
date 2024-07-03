package com.multi.mini.common.service;

import com.multi.mini.common.model.dto.EmailDTO;
import org.springframework.stereotype.Service;

@Service
public interface TempPasswordService {

    void sendTempPassword(EmailDTO emailDTO) throws Exception;
    String createTempPassword();


}
