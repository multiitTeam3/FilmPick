package com.multi.mini.common.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {
    @GetMapping(value = {"/", "/home"})
    public String main(Model model) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("id", id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

//        Iterator<? extends  GrantedAuthority> iter = authorities.iterator();
//        GrantedAuthority auth = iter.next();

//        String role = auth.getAuthority();

        List<String> roles = new ArrayList<>();
        if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
                roles.add(authority.getAuthority());
            }
        }

        model.addAttribute("role", roles);
        return "main/main";
    }

    @PostMapping("/clearMessage")
    public void clearMessage(HttpSession session) {
        session.removeAttribute("msg");
    }

}
