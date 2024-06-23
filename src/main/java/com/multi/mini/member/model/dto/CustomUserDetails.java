package com.multi.mini.member.model.dto;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private MemberDTO memberDTO;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // memberDTO의 roles 리스트에서 각 역할을 GrantedAuthority로 변환
        for (RoleDTO role : memberDTO.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return memberDTO.getPassword();
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

    public String getImg() {
        return memberDTO.getImg();
    }

    public String getAddress() {
        return  memberDTO.getAddress();
    }

    public Date getCreateDate() {
        return memberDTO.getCreateDate();
    }

}