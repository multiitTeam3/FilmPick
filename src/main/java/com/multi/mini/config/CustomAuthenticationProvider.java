package com.multi.mini.config;

import com.multi.mini.common.service.CustomUserDetailsService;
import com.multi.mini.member.model.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 입력한 ID와 비밀번호 변수에 담기
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();

        // CustomUserDetailsService로 변환
        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
        
        // loadUserByUsername에서 username(email)이 DB에 없어서 반환 못한 경우 예외 처리
        if (customUserDetails == null) {
            throw new AuthenticationException("User not found") {};
        }

        // 비밀번호 or 임시 비밀번호 둘 중 하나가 맞으면 true처리
        boolean passwordMatches = passwordEncoder.matches(password, customUserDetails.getPassword()) ||
                (password.equals(customUserDetails.getTempPassword()) && // 임시 비밀번호 일치
                        !customUserDetails.getTempPasswordIsUse() && // 임시 비밀번호 사용 전
                        customUserDetails.getTempExpDate().isAfter(LocalDateTime.now())); // 만료시간이 현재시간 이전인지 확인

        // 비밀번호 false 일 시 예외 처리
        if (!passwordMatches) {
            throw new AuthenticationException("Wrong password") {};
        }

        // 인증 정보가 담긴 객체 반환
        return new UsernamePasswordAuthenticationToken(customUserDetails, password, customUserDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}