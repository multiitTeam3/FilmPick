package com.multi.mini.member.model.dto;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private MemberDTO memberDTO;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return memberDTO.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return memberDTO.getPw();
    }

    @Override
    public String getUsername() {
        return memberDTO.getId();
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

    public String getUserName() {
        return memberDTO.getUserName();
    }
}
