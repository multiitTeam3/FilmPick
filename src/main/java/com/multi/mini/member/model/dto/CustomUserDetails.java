package com.multi.mini.member.model.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final MemberDTO memberDTO;
    private final Boolean tempPassword;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // memberDTO의 roles 리스트에서 각 역할을 GrantedAuthority로 변환
        for (RoleDTO role : memberDTO.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }

    public CustomUserDetails(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
        this.tempPassword = false;
    }

    @Override
    public String getPassword() {
        return memberDTO.getPassword();
    }

    public String getTempPassword() {
        return memberDTO.getTempPassword();
    }

    public boolean getTempPasswordIsUse() {
        return memberDTO.getTempPasswordIsUse();
    }

    public LocalDateTime getTempExpDate() {
        return memberDTO.getTempExpDate().toLocalDateTime();
    }

    @Override
    public String getUsername() {
        return memberDTO.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getMemberNo() {
        return memberDTO.getMemberNo();
    }

    public String getNickName() {
        return memberDTO.getUserName();
    }

    public String getTel() {
        return memberDTO.getTel();
    }

    public String getAddress() {
        return  memberDTO.getAddress();
    }

    public LocalDateTime getCreateDate() {
        return memberDTO.getCreateDate().toLocalDateTime();
    }

    public int getPoint() {
        return  memberDTO.getPoint();
    }

    public void update(MemberDTO updateDTO) {
        this.memberDTO.setUserName(updateDTO.getUserName());
        this.memberDTO.setTel(updateDTO.getTel());
        this.memberDTO.setAddress(updateDTO.getAddress());
        this.memberDTO.setPoint(updateDTO.getPoint());
    }
}