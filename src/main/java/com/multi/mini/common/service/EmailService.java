package com.multi.mini.common.service;

import com.multi.mini.common.model.dto.EmailDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmail(EmailDTO emailDTO) throws Exception;
}
