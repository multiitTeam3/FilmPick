package com.multi.mini.common.service;

import com.multi.mini.common.model.mapper.AuthMapper;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.member.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO userDTO = authMapper.selectMemberByEmail(username);

        if (userDTO != null) {
            return new CustomUserDetails(userDTO);
        }
        return null;
    }
}
